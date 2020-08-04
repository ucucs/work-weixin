package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowTag {

  @JsonProperty("group_name")
  private String groupName;

  @JsonProperty("tag_name")
  private String tagName;

  private Integer type;
}
