package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowUser {

  @JsonProperty("userid")
  private String userId;

  private String remark;
  private String description;

  @JsonProperty("createtime")
  private Long createTime;

  private String state;

  @JsonProperty("remark_corp_name")
  private String remarkCorpName;

  @JsonProperty("remark_mobiles")
  private String[] remarkMobiles;

  @JsonProperty("oper_userid")
  private String operUserId;

  @JsonProperty("add_way")
  private Integer addWay;

  private List<FollowTag> tags;
}
