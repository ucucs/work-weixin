package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowTag {

  @JsonAlias(value = "group_name")
  private String groupName;

  @JsonAlias(value = "tag_name")
  private String tagName;

  private Integer type;
}
