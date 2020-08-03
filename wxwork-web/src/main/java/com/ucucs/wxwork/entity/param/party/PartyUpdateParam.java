package com.ucucs.wxwork.entity.param.party;

import com.ucucs.wxwork.entity.param.ValidParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class PartyUpdateParam implements ValidParam {

  private Long id;
  private String name;
  private String enName;
  private Long parentId;
  private Long order;

  @Override
  public void validParam() {
    Assert.notNull(id, "部门ID不能为空");
  }
}
