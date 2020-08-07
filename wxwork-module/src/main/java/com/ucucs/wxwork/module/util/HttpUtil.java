package com.ucucs.wxwork.module.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

  public static String encode(String content) {
    try {
      return URLEncoder.encode(content, StandardCharsets.UTF_8.name());
    } catch (Exception ex) {
      LOGGER.error("Url encode error {}", content, ex);
    }
    return content;
  }
}
