package com.ucucs.wxwork.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkUser {
  private String userId;

  private String name;

  private String englishName;

  private String mobile;

  private String thumbAvatar;

  private Integer status;

  private String alias;

  private Integer hideMobile;

  private String openUserid;

  private String unionId;
}
