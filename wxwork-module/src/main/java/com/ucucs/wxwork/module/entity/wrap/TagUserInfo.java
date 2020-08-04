package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagUserInfo {

  @JsonProperty("userlist")
  private List<UserSimple> userlist;

  @JsonProperty("partylist")
  private List<Long> partyIds;

  @JsonProperty("tagname")
  private String tagName;

  public static TagUserInfo parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, TagUserInfo.class);
  }
}
