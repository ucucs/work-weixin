package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.module.entity.wrap.GroupChatMember;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class WxExternalGroupChatDetail {

  @JsonAlias("chat_id")
  private String chatId;

  @JsonAlias("name")
  private String name;

  @JsonAlias("owner")
  private String owner;

  @JsonAlias("create_time")
  private Long createTime;

  @JsonAlias("notice")
  private String notice;

  @JsonAlias("member_list")
  private List<GroupChatMember> memberList;

  public static WxExternalGroupChatDetail parseMsgBody(JsonNode msgNode) {
    JsonNode chatNode = msgNode.get("group_chat");
    return JsonUtil.nodeToBean(chatNode, WxExternalGroupChatDetail.class);
  }
}
