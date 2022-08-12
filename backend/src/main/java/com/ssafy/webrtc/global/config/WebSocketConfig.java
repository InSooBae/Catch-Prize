package com.ssafy.webrtc.global.config;

import com.ssafy.webrtc.domain.game.stomp.handler.StompHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // sockjs 지원
        registry.addEndpoint("/room-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
        // 그냥 websocket 지원
//        registry.addEndpoint("/room-stomp").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // websocket 메세지 전달시 앞에 붙이는 prefix
        registry.setApplicationDestinationPrefixes("/pub");
        // 구독 topic 등록시 앞에 붙이는 prefix
        registry.enableSimpleBroker("/sub");
    }

    // 인터셉터 등록
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors(stompHandler);
    }
}
