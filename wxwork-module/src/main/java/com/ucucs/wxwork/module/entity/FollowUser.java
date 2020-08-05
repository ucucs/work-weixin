package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowUser {

  @JsonAlias(value = "userid")
  private String userId;

  private String remark;
  private String description;

  @JsonAlias(value = "createtime")
  private Long createTime;

  private String state;

  @JsonAlias(value = "remark_corp_name")
  private String remarkCorpName;

  @JsonAlias(value = "remark_mobiles")
  private String[] remarkMobiles;

  @JsonAlias(value = "oper_userid")
  private String operUserId;

  @JsonAlias(value = "add_way")
  private Integer addWay;

  private List<FollowTag> tags;
}
