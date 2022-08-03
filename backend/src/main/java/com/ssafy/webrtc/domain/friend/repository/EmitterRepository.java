package com.ssafy.webrtc.domain.friend.repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepository {

    private final Map<UUID, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter save(UUID userId, SseEmitter sseEmitter) {
        emitters.put(userId, sseEmitter);
        return sseEmitter;
    }

    public Optional<SseEmitter> findById(UUID id) {
        return Optional.ofNullable(emitters.get(id));
    }

    public void deleteById(UUID userId) {
        emitters.remove(userId);
    }
}