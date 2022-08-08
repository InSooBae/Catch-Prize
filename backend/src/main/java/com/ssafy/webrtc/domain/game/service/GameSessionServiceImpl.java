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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameSessionServiceImpl implements GameSessionService{

    private final GameSessionRedisRepository gameSessionRedisRepository;

    private final OpenVidu openVidu;


    @Override
    public GameSession makeSession(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) throws OpenViduJavaClientException, OpenViduHttpException {
//        String id = "";
//
//        do {
//            id = RoomIdUtils.randomRoomId(gameSessionRequestDto.getAccessType());
//
//            // id 중복 없으면
//        } while (!gameSessionRedisRepository.findById(id).equals(Optional.empty()));

        if (gameSessionRedisRepository.findByCreator(user.getName()).size() > 1) {
            throw new DataIntegrityViolationException("더 이상 방을 만들 수 없습니다!");
        }

        Session openViduSession = openVidu.createSession();

        String roomId = RoomIdUtils.getIdPrefix(gameSessionRequestDto.getAccessType()) + openViduSession.getSessionId().split("_")[1];

        LocalDateTime createdTime = LocalDateTime.now();

        GameSession gameSession = GameSession
                .builder()
                .roomId(roomId)
                .creator(user.getName())
                .roomName(gameSessionRequestDto.getRoomName())
                .roomType(gameSessionRequestDto.getRoomType())
                .accessType(gameSessionRequestDto.getAccessType())
                .createTime(createdTime)
                .build();


        return GameSession.of(gameSessionRedisRepository.save(GameSessionDao.of(gameSession)));
    }


    @Override
    public GameSessionJoinResponseDto addUser(String roomId, String nickname) {
        OpenViduRole role = OpenViduRole.PUBLISHER;

        String serverData = "{\"serverData\": \"" + nickname + "\"}";

        // Build connectionProperties object with the serverData and the role
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        GameSession gameSession = findById(roomId);
        try {
            // ex> wss://localhost:4443?sessionId=ses_Ogize1yQIj&token=tok_A1c0pNsLJFwVJTeb
            String token = gameSession.getSession().createConnection(connectionProperties).getToken();

            // ex> tok_A1c0pNsLJFwVJTeb
            String userId = UrlUtils.getUrlQueryParam(token, "token")
                    .orElseThrow(() -> new EmptyResultDataAccessException(1))
                    .substring(4);

            gameSession.getMapSessionNamesTokens().put(token, role);

            if (gameSession.getMapSessionNamesTokens().size() == 1) {
                gameSession.setMasterId(userId);
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
            return GameSessionJoinResponseDto
                    .builder().build();
        }
    }

    @Override
    public boolean removeUser(String roomId, String token) {
        GameSession gameSession = findById(roomId);
        Session session = gameSession.getSession();
        Map<String, OpenViduRole> mapSessionNamesTokens = gameSession.getMapSessionNamesTokens();

        // If the session exists ("TUTORIAL" in this case)
        if (session == null || mapSessionNamesTokens == null) {
            // The SESSION does not exist
            System.out.println("Problems in the app server: the SESSION does not exist");
            return false;
        }
        if (mapSessionNamesTokens.remove(token) == null) {
            // The TOKEN wasn't valid
            System.out.println("Problems in the app server: the TOKEN wasn't valid");
            return false;
        }
        // User left the session
        if (mapSessionNamesTokens.isEmpty()) {
            removeSession(gameSession);
        }
        return true;
    }
    @Override
    public GameSession enterSession(CustomUserDetails user, String roomId) {

        GameSession gameSession = findById(roomId);

//        Player
//                .builder()
//                .id()
        if (gameSession.getPlayerMap().size() > 6) {
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
        return GameSession.of(gameSessionRedisRepository
                .findById(roomId)
                .orElseThrow(() -> new EmptyResultDataAccessException("방을 찾을 수 없습니다!", 1)));
    }

    @Override
    public void update(GameSession update) {
//        GameSessionDao updateDto = GameSessionDaoMapper.INSTANCE.toDao(update);
//        gameSessionRedisRepository.save(updateDto);
    }
}