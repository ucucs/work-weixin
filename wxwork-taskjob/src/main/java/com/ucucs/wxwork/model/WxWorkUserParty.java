package com.ucucs.wxwork.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxWorkUserParty extends WxWorkUserPartyKey {
  private Integer order;

  private Integer chargeLeader;

  private Integer mainParty;
}
