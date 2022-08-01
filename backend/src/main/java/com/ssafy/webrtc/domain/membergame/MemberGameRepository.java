package com.ssafy.webrtc.domain.membergame;

import com.ssafy.webrtc.domain.membergame.entity.MemberGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberGameRepository extends JpaRepository<MemberGame, Long> {

    @Query(value = "select mg from MemberGame mg where mg.member = :memberId order by mg.createDate LIMIT :size", nativeQuery = true)
    List<MemberGameDto> findByMemberIdOrOrderByCreateDateDesc(@Param("memberId") UUID memberId, @Param("size") Integer size);

}
