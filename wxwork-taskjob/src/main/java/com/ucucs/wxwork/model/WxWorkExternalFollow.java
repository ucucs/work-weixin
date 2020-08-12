package com.ucucs.wxwork.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkExternalFollow {
  private Long id;

  private String externalUserId;

  private String userId;

  private String remark;

  private String description;

  private Date createTime;

  private String state;

  private String remarkCorpName;

  private String remarkMobiles;

  private String operUserid;

  private Integer addWay;
}
