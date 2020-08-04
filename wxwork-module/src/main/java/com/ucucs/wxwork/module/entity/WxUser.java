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
public class WxUser implements WxBodyConvert {

  @JsonProperty("userid")
  private String userId;

  private String name;

  @JsonProperty("department")
  private Long[] partyIds;

  @JsonProperty("order")
  private Integer[] orders;

  private String position;
  private String mobile;
  private Integer gender;
  private String email;

  private String address;

  @JsonProperty("thumb_avatar")
  private String avatarMediaId;

  private Integer enable;

  @JsonProperty("to_invite")
  private Boolean toInvite;

  private String alias;

  @JsonProperty("is_leader_in_dept")
  private Integer[] isLeaderInParty;

  @JsonProperty("hide_mobile")
  private Integer hideMobile;

  @JsonProperty("english_name")
  private String englishName;

  private String telephone;

  @JsonProperty("external_position")
  private String externalPosition;

  @JsonProperty("external_profile")
  private Map<String, Object> externalProfile;

  @JsonProperty("main_department")
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
