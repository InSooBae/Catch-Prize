package com.ssafy.webrtc.domain.game.repository;

import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRedisRepository extends CrudRepository<GameSession, String> {

    List<GameSession> findByCreator(String memberNickname);
}
