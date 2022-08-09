package com.ssafy.webrtc.domain.game.service;

import com.ssafy.webrtc.domain.game.dao.GameSessionDao;
import com.ssafy.webrtc.domain.game.dto.GameSessionJoinResponseDto;
import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.entity.Player;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.repository.GameSessionRedisRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.util.RoomIdUtils;
import com.ssafy.webrtc.global.util.UrlUtils;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameSessionServiceImpl implements GameSessionService{

    public static final int MAX_PLAYER_COUNT = 6;
    private final GameSessionRedisRepository gameSessionRedisRepository;

    private final OpenVidu openVidu;


    @Override
    public GameSession makeSession(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) throws OpenViduJavaClientException, OpenViduHttpException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        System.out.println(userDetails.getUsername());
        if (gameSessionRedisRepository.findByCreator(user.getUsername()).size() > 1) {
            throw new DataIntegrityViolationException("더 이상 방을 만들 수 없습니다!");
        }

        Session openViduSession = openVidu.createSession();

        String roomId = RoomIdUtils.getIdPrefix(gameSessionRequestDto.getAccessType()) + openViduSession.getSessionId().split("_")[1];

        LocalDateTime createdTime = LocalDateTime.now();


        GameSession gameSession = GameSession
                .builder(roomId, user.getUsername(), gameSessionRequestDto.getRoomName(), gameSessionRequestDto.getAccessType(), createdTime, openViduSession, null)
                .state(GameState.WAIT)
                .maxParticipants(gameSessionRequestDto.getMaxParticipants())
                .finishedTime(createdTime)
                .build();

        return toEntity(gameSessionRedisRepository.save(GameSessionDao.of(gameSession)));
    }

    @Override
    public List<GameSession> findAll() {
        return null;
    }

//    public List<GameSession> findAll() {
//        List<GameSessionDao> allOfGameSession = gameSessionRedisRepository.findAll();
//        return allOfGameSession.stream().map(GameSession::of).collect(Collectors.toCollection(ArrayList::new));
//    }

    private GameSession toEntity(GameSessionDao dao) {
        Session entitySession = null;
        for (Session session : openVidu.getActiveSessions()) {
            if (session.getSessionId().equals(dao.getSessionId())) {
                entitySession = session;
                break;
            }
        }
        if (entitySession == null) {
            throw new NullPointerException();
        }

        Map<String, Player> playerMap = dao.getPlayerMap();
        if (playerMap == null) {
            playerMap = new LinkedHashMap<>();
        }



        return GameSession.builder(dao.getRoomId(), dao.getCreator(), dao.getRoomName(),
                        dao.getAccessType(), dao.getCreateTime(), entitySession, playerMap)
                .finishedTime(dao.getFinishedTime())
                .phase(dao.getPhase())
                .lastEnter(dao.getLastEnter())
                .state(dao.getState())
                .hostId(dao.getHostId())
                .build();
    }


    @Override
    public GameSessionJoinResponseDto addUser(String roomId, String nickname) {

        GameSession gameSession = findById(roomId);

        validateCanJoin(gameSession);

        OpenViduRole role = OpenViduRole.PUBLISHER;

        String serverData = "{\"serverData\": \"" + nickname + "\"}";

        // Build connectionProperties object with the serverData and the role
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        try {
            // ex> wss://localhost:4443?sessionId=ses_Ogize1yQIj&token=tok_A1c0pNsLJFwVJTeb
            String token = gameSession.getSession().createConnection(connectionProperties).getToken();

            // ex> tok_A1c0pNsLJFwVJTeb
            String userId = UrlUtils.getUrlQueryParam(token, "token")
                    .orElseThrow(() -> new EmptyResultDataAccessException(1))
                    .substring(4);


            Player player = Player.of(userId, nickname, token, role);

            gameSession.getPlayerMap().put(userId, player);

            if (gameSession.getPlayerMap().size() == 1) {
                gameSession.setHostId(userId);
            }
            update(gameSession);

            return GameSessionJoinResponseDto
                    .builder()
                    .userId(userId)
                    .token(token)
                    .build();
        } catch (OpenViduJavaClientException e1) {
            // If internal error generate an error message and return it to client
            return GameSessionJoinResponseDto
                    .builder().build();
        } catch (OpenViduHttpException e2) {
            if (404 == e2.getStatus()) {
                removeSession(gameSession);
            }
            throw new RuntimeException(e2.getMessage());
        }
    }

    @Override
    public GameSession removeUser(String roomId, String userId) {
        GameSession gameSession = findById(roomId);
        Session session = gameSession.getSession();
        Map<String, Player> playerMap = gameSession.getPlayerMap();

        // If the session exists ("TUTORIAL" in this case)
        if (session == null || playerMap == null) {
            // The SESSION does not exist
            log.info("Problems in the app server: the SESSION does not exist");
            throw new NullPointerException("세션이 존재하지 않음");
        }
        if (playerMap.remove(userId) == null) {
            // The TOKEN wasn't valid
            log.info("Problems in the app server: the TOKEN - {} wasn't valid", userId);
        }
        // User left the session
        if (playerMap.isEmpty()) {
            removeSession(gameSession);
        } else {
            // 방장 나가면 남은 사람 중 한명 호스트로 바꾸기
            makeHostForLeftUser(userId, gameSession, playerMap);
        }
        return gameSession;
    }

    private void makeHostForLeftUser(String userId, GameSession gameSession, Map<String, Player> playerMap) {
        if (userId.equals(gameSession.getHostId())) {
            gameSession.setHostId(playerMap.keySet().iterator().next());
        }
    }

    @Override
    public GameSession enterSession(CustomUserDetails user, String roomId) {

        GameSession gameSession = findById(roomId);

//        Player
//                .builder()
//                .id()
        if (gameSession.getPlayerMap().size() > MAX_PLAYER_COUNT) {
            throw new DataIntegrityViolationException("방 정원이 가득 찼습니다.");
        }

        if (gameSession.getState().equals(GameState.STARTED)) {
            throw new IllegalStateException("게임이 이미 시작됐습니다.");
        }

        return gameSession;
    }

    @Override
    public void removeSession(GameSession gameSession) {
        gameSessionRedisRepository.deleteById(gameSession.getRoomId());
    }

    @Override
    public GameSession findById(String roomId) {
        return toEntity(gameSessionRedisRepository
                .findById(roomId)
                .orElseThrow(() -> new EmptyResultDataAccessException("방을 찾을 수 없습니다!", 1)));
    }

    @Override
    public void update(GameSession update) {
//        GameSessionDao updateDto = GameSessionDaoMapper.INSTANCE.toDao(update);
//        gameSessionRedisRepository.save(updateDto);
    }

    @Override
    public GameState getGameSessionState(String roomId) {
        GameSession gameSession = findById(roomId);

        validateCanJoin(gameSession);

        return gameSession.getState();
    }

    private void validateCanJoin(GameSession gameSession) {
        if (gameSession.getState() == GameState.STARTED) {
            throw new RuntimeException("이미 게임이 시작됐습니다!");
        }

        if (gameSession.getPlayerMap().size() > gameSession.getMaxParticipants()) {
            throw new RuntimeException("정원 초과했습니다.");
        }
    }
}
