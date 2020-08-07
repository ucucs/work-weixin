package com.ucucs.wxwork.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkParty {
  private Long id;

  private String name;

  private String enName;

  private Long parentId;

  private Integer order;
}
