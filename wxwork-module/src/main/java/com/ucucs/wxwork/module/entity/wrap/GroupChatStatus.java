package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupChatStatus {

  @JsonAlias(value = "chat_id")
  private String chatId;

  @JsonAlias(value = "status")
  private Integer status;

  public static List<GroupChatStatus> parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("group_chat_list");
    return JsonUtil.nodeToBeanList(userListNode, GroupChatStatus.class);
  }
}
