package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimple {

  @JsonAlias(value = "userid")
  private String userId;

  private String name;

  @JsonAlias(value = "department")
  private Long[] partyIds;

  @JsonAlias(value = "open_userid")
  private String openUserId;

  public static List<UserSimple> parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("userlist");
    return JsonUtil.nodeToBeanList(userListNode, UserSimple.class);
  }
}
