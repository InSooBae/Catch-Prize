package com.ssafy.webrtc.domain.game.repository;

import com.ssafy.webrtc.domain.game.dao.GameSessionDao;
import com.ssafy.webrtc.domain.game.entity.GameSession;
import com.ssafy.webrtc.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRedisRepository extends CrudRepository<GameSessionDao, String> {

    List<GameSessionDao> findByCreator(String memberNickname);

    @Override
    List<GameSessionDao> findAll();
}
