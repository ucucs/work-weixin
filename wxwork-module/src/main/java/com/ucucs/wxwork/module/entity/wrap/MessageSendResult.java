package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageSendResult {

  @JsonProperty("invalidlist")
  private String invalidUserIds;

  @JsonProperty("invalidparty")
  private String invalidPartyIds;

  @JsonProperty("invalidtag")
  private String invalidTags;

  public static MessageSendResult parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, MessageSendResult.class);
  }
}
