package com.ssafy.webrtc.global.config;

import io.openvidu.java.client.OpenVidu;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Entity 변환용 ModelMapper 추가
    // Dto -> Setter, AllArgs, NoArgs & Entity -> Getter
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenVidu openVidu(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openViduUrl) {
        return new OpenVidu(openViduUrl, secret);
    }

}
