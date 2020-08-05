package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class UnAssignInfo {

  @JsonAlias(value = "handover_userid")
  private String handOverUserId;

  @JsonAlias(value = "external_userid")
  private String externalUserId;

  @JsonAlias(value = "dimission_time")
  private Long dimissionTime;
}
