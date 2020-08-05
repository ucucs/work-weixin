package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("session_key")
  private String sessionKey;

  @JsonProperty("userid")
  private String userId;

  @JsonProperty("corpid")
  private String corpId;

  public static CodeToSessionResult parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, CodeToSessionResult.class);
  }
}
