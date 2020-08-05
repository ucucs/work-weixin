package com.ucucs.wxwork.module.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * coding.
 *
 * @author ucucs.
 */
public class RandomUtil {

  private static final String RANDOM_ALPHA =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static final Random RANDOM = ThreadLocalRandom.current();

  public static String getRandomStr() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      sb.append(RANDOM_ALPHA.charAt(RANDOM.nextInt(RANDOM_ALPHA.length())));
    }
    return sb.toString();
  }
}
