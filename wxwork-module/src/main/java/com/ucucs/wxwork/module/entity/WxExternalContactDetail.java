package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("external_userid")
  private String externalUserId;

  @JsonProperty("position")
  private String position;

  @JsonProperty("name")
  private String name;

  @JsonProperty("avatar")
  private String avatar;

  @JsonProperty("corp_name")
  private String corpName;

  @JsonProperty("corp_full_name")
  private String corpFullName;

  @JsonProperty("type")
  private Integer type;

  @JsonProperty("gender")
  private Integer gender;

  @JsonProperty("unionid")
  private String unionId;

  @JsonProperty("follow_user")
  private List<FollowUser> followedUsers;

  @JsonProperty("external_profile")
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
