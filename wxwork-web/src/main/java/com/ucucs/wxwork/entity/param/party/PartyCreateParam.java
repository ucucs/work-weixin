package com.ucucs.wxwork.entity.param.party;

import com.ucucs.wxwork.entity.param.ValidParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class PartyCreateParam implements ValidParam {

  private Long id;
  private String name;
  private String enName;
  private Long parentId;
  private Long order;

  @Override
  public void validParam() {
    Assert.hasLength(name, "部门名称不能为空");
    Assert.notNull(parentId, "上级部门ID不能为空");
  }
}
