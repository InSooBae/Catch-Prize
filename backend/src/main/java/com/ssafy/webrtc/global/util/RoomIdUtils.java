package com.ssafy.webrtc.global.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RoomIdUtils {
  public static String randomRoomId() {
    return RandomStringUtils.randomAlphanumeric(6);
  }
}