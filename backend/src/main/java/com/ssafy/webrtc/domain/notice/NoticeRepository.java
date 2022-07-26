package com.ssafy.webrtc.domain.notice;

import com.ssafy.webrtc.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
