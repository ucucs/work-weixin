package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagOperateResult {

  @JsonAlias(value = "invalidlist")
  private String invalidUserIds;

  @JsonAlias(value = "invalidparty")
  private String[] invalidPartyIds;

  public static TagOperateResult parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, TagOperateResult.class);
  }
}
