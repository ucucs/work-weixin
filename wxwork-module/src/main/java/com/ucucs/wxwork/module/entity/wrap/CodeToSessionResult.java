package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class CodeToSessionResult {

  @JsonAlias(value = "session_key")
  private String sessionKey;

  @JsonAlias(value = "userid")
  private String userId;

  @JsonAlias(value = "corpid")
  private String corpId;

  public static CodeToSessionResult parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, CodeToSessionResult.class);
  }
}
