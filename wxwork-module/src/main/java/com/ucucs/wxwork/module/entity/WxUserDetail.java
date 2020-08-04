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
public class WxUserDetail {

  @JsonProperty("userid")
  private String userId;

  private String name;

  @JsonProperty("department")
  private Long[] partyIds;

  private String position;
  private String mobile;
  private Integer gender;
  private String email;
  private String avatar;

  @JsonProperty("thumb_avatar")
  private String thumbAvatar;

  private String address;

  private Integer status;

  private String alias;

  @JsonProperty("is_leader_in_dept")
  private Integer[] isLeaderInParty;

  @JsonProperty("hide_mobile")
  private Integer hideMobile;

  @JsonProperty("english_name")
  private String englishName;

  private String telephone;

  @JsonProperty("qr_code")
  private String qrCode;

  @JsonProperty("external_position")
  private String externalPosition;

  @JsonProperty("external_profile")
  private Map<String, Object> externalProfile;

  @JsonProperty("open_userid")
  private String openUserId;

  @JsonProperty("main_department")
  private String mainPartyId;

  public static List<WxUserDetail> parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("userlist");
    return JsonUtil.nodeToBeanList(userListNode, WxUserDetail.class);
  }
}
