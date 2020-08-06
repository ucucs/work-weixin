package com.ucucs.wxwork.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wxwork.config")
public class WxConfigProperties {

  private String baseUrl = null;
  private String corpId;
  private Integer agentId;
  private String notifyToken;
  private String notifyAesKey;
  private Map<String, String> categorySecrets;
}
