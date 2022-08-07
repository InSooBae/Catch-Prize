package com.ssafy.webrtc.global.util;

import com.ssafy.webrtc.domain.game.enums.AccessType;
import org.apache.commons.lang3.RandomStringUtils;

public class RoomIdUtils {
  public static String randomRoomId(AccessType accessType) {
    return getIdPrefix(accessType) + RandomStringUtils.randomAlphanumeric(6);
  }

  public static String getIdPrefix(AccessType accessType) {
    if (accessType == AccessType.PRIVATE) {
      // Secret
      return "S";
    } else {
      // Basic ('P'ublic , 'P'riate)
      return "B";
    }
  }
}