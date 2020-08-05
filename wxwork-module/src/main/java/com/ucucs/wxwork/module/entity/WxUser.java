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
public class WxUser implements WxBodyConvert {

  @JsonAlias(value = "userid")
  private String userId;

  private String name;

  @JsonAlias(value = "department")
  private Long[] partyIds;

  @JsonAlias(value = "order")
  private Integer[] orders;

  private String position;
  private String mobile;
  private Integer gender;
  private String email;

  private String address;

  @JsonAlias(value = "thumb_avatar")
  private String avatarMediaId;

  private Integer enable;

  @JsonAlias(value = "to_invite")
  private Boolean toInvite;

  private String alias;

  @JsonAlias(value = "is_leader_in_dept")
  private Integer[] isLeaderInParty;

  @JsonAlias(value = "hide_mobile")
  private Integer hideMobile;

  @JsonAlias(value = "english_name")
  private String englishName;

  private String telephone;

  @JsonAlias(value = "external_position")
  private String externalPosition;

  @JsonAlias(value = "external_profile")
  private Map<String, Object> externalProfile;

  @JsonAlias(value = "main_department")
  private String mainPartyId;

  @Override
  public Map<String, Object> wrapMsgBody() {
    return JsonUtil.beanToMap(this);
  }

  public static List<WxUser> parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("userlist");
    return JsonUtil.nodeToBeanList(userListNode, WxUser.class);
  }

  public static WxUser parseSinge(JsonNode msgNode) {
    return JsonUtil.nodeToBean(msgNode, WxUser.class);
  }
}
