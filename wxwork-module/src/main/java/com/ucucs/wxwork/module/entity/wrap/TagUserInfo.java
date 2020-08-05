package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagUserInfo {

  @JsonAlias(value = "userlist")
  private List<UserSimple> userlist;

  @JsonAlias(value = "partylist")
  private List<Long> partyIds;

  @JsonAlias(value = "tagname")
  private String tagName;

  public static TagUserInfo parseMsgBody(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, TagUserInfo.class);
  }
}
