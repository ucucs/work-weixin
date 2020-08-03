package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxUser implements WxBodyConvert {

  private String userId;
  private String name;
  private Long[] partyIds;
  private Integer[] orders;
  private String position;
  private String mobile;
  private Gender gender;
  private String email;
  private String avatar;
  private String thumbAvatar;

  private String address;
  private String avatarMediaId;
  private Integer status;
  private Integer enable;

  private String alias;
  private Integer isLeader;

  private Integer[] isLeaderInParty;
  private Integer hideMobile;
  private String englishName;
  private String telephone;
  private String qrCode;
  private Boolean toInvite;

  private final List<FeatureAttr> extAttrs = new ArrayList<>();
  private List<ExternalAttr> externalAttrs = new ArrayList<>();

  private String externalPosition;
  private String externalCorpName;

  private String openUserId;

  @Override
  public Map<String, Object> wrapMsgBody() {
    return null;
  }

  public static List<WxUser> parseSimpleMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("userlist");
    List<WxUser> userList = new ArrayList<>();
    userListNode.forEach(userNode -> userList.add(parseUserNode(userNode)));
    return userList;
  }

  public static WxUser parseUserNode(JsonNode userNode) {
    WxUser party = new WxUser();
    party.setUserId(userNode.get("userid").asText());
    return party;
  }
}
