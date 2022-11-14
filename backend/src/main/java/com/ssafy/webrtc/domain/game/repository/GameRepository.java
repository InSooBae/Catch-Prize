package com.ssafy.webrtc.domain.game.repository;

import com.ssafy.webrtc.domain.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
   Game findByTitle(String title);

}
