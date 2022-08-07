package com.ssafy.webrtc.domain.game.service;

import com.ssafy.webrtc.domain.game.dto.GameSessionRequestDto;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.game.enums.GameState;
import com.ssafy.webrtc.domain.game.repository.GameSessionRedisRepository;
import com.ssafy.webrtc.global.security.auth.CustomUserDetails;
import com.ssafy.webrtc.global.util.RoomIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameSessionServiceImpl implements GameSessionService{

    private final GameSessionRedisRepository gameSessionRedisRepository;


    @Override
    public GameSession makeRoom(CustomUserDetails user, GameSessionRequestDto gameSessionRequestDto) {
        String id = "";

        do {
            id = RoomIdUtils.randomRoomId(gameSessionRequestDto.getAccessType());

            // id 중복 없으면
        } while (!gameSessionRedisRepository.findById(id).equals(Optional.empty()));

        if (gameSessionRedisRepository.findByCreator(user.getName()).size() > 1) {
            throw new DataIntegrityViolationException("더 이상 방을 만들 수 없습니다!");
        }

        return gameSessionRedisRepository.save(GameSession
                .builder()
                .roomId(id)
                .creator(user.getName())
                .roomType(gameSessionRequestDto.getRoomType())
                .accessType(gameSessionRequestDto.getAccessType())
                .build());
    }

    @Override
    public GameSession enterRoom(CustomUserDetails user, String roomId) {

        GameSession gameSession = gameSessionRedisRepository
                .findById(roomId)
                .orElseThrow(() -> new EmptyResultDataAccessException("방을 찾을 수 없습니다!", 1));

        if (gameSession.getPlayers().size() > 6) {
            throw new DataIntegrityViolationException("방 정원이 가득 찼습니다.");
        }

        if (gameSession.getState().equals(GameState.STARTED)) {
            throw new IllegalStateException("게임이 이미 시작됐습니다.");
        }

        return gameSession;
    }
}
