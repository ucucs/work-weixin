package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucucs.wxwork.module.entity.UnAssignInfo;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class WxExternalUnAssignPage {

  @JsonProperty("info")
  private List<UnAssignInfo> unAssignInfos;

  @JsonProperty("is_last")
  private Boolean lastFlag;
}
