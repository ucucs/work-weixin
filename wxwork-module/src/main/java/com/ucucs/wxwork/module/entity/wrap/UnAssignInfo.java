package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("handover_userid")
  private String handOverUserId;

  @JsonProperty("external_userid")
  private String externalUserId;

  @JsonProperty("dimission_time")
  private Long dimissionTime;
}
