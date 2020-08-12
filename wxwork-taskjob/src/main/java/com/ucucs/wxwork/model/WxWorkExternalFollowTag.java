package com.ucucs.wxwork.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkExternalFollowTag {
  private Long id;

  private String externalUserId;

  private String userId;

  private Integer type;

  private String tagName;

  private String groupName;
}
