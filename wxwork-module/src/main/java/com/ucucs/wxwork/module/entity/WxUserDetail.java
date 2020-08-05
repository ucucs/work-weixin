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
public class WxUserDetail {

  @JsonAlias(value = "userid")
  private String userId;

  private String name;

  @JsonAlias(value = "department")
  private Long[] partyIds;

  private String position;
  private String mobile;
  private Integer gender;
  private String email;
  private String avatar;

  @JsonAlias(value = "thumb_avatar")
  private String thumbAvatar;

  private String address;

  private Integer status;

  private String alias;

  @JsonAlias(value = "is_leader_in_dept")
  private Integer[] isLeaderInParty;

  @JsonAlias(value = "hide_mobile")
  private Integer hideMobile;

  @JsonAlias(value = "english_name")
  private String englishName;

  private String telephone;

  @JsonAlias(value = "qr_code")
  private String qrCode;

  @JsonAlias(value = "external_position")
  private String externalPosition;

  @JsonAlias(value = "external_profile")
  private Map<String, Object> externalProfile;

  @JsonAlias(value = "open_userid")
  private String openUserId;

  @JsonAlias(value = "main_department")
  private String mainPartyId;

  public static List<WxUserDetail> parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("userlist");
    return JsonUtil.nodeToBeanList(userListNode, WxUserDetail.class);
  }
}
