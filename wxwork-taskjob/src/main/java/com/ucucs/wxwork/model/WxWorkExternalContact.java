package com.ucucs.wxwork.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkExternalContact {
  private String externalUserId;

  private String position;

  private String name;

  private String avatar;

  private String corpName;

  private String corpFullName;

  private Integer type;

  private Integer gender;

  private String unionId;

  private String externalProfile;
}
