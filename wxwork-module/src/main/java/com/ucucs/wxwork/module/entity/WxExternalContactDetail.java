package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxExternalContactDetail {

  @JsonAlias(value = "external_userid")
  private String externalUserId;

  @JsonAlias(value = "position")
  private String position;

  @JsonAlias(value = "name")
  private String name;

  @JsonAlias(value = "avatar")
  private String avatar;

  @JsonAlias(value = "corp_name")
  private String corpName;

  @JsonAlias(value = "corp_full_name")
  private String corpFullName;

  @JsonAlias(value = "type")
  private Integer type;

  @JsonAlias(value = "gender")
  private Integer gender;

  @JsonAlias(value = "unionid")
  private String unionId;

  @JsonAlias(value = "follow_user")
  private List<FollowUser> followedUsers;

  @JsonAlias(value = "external_profile")
  private Map<String, Object> externalProfile;

  public static WxExternalContactDetail parseMsgBody(JsonNode msgNode) {
    JsonNode contactNode = msgNode.get("external_contact");
    WxExternalContactDetail detail =
        JsonUtil.nodeToBean(contactNode, WxExternalContactDetail.class);

    ArrayNode followNode = msgNode.withArray("follow_user");
    List<FollowUser> followedUsers = JsonUtil.nodeToBeanList(followNode, FollowUser.class);
    detail.setFollowedUsers(followedUsers);
    return detail;
  }
}
