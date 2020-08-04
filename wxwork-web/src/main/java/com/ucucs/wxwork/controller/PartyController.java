package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.entity.param.party.PartyCreateParam;
import com.ucucs.wxwork.entity.param.party.PartyUpdateParam;
import com.ucucs.wxwork.module.entity.WxParty;
import com.ucucs.wxwork.module.util.BeanMapper;
import com.ucucs.wxwork.service.PartyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/dept")
public class PartyController {

  private final PartyService partyService;

  @GetMapping("/list")
  public Result<?> getPartyList(@RequestParam Long partyId) {
    Assert.notNull(partyId, "部门ID不能为空");
    List<WxParty> deptList = partyService.getPartyList(partyId);
    return Result.success(deptList);
  }

  @PostMapping("/create")
  public Result<?> createParty(@RequestBody PartyCreateParam partyParam) {
    WxParty party = BeanMapper.mapper(partyParam, WxParty.class);
    Long partyId = partyService.createParty(party);
    return Result.success(partyId);
  }

  @PostMapping("/update")
  public Result<?> updateParty(@RequestBody PartyUpdateParam partyParam) {
    WxParty party = BeanMapper.mapper(partyParam, WxParty.class);
    partyService.updateParty(party);
    return Result.success();
  }

  @GetMapping("/delete")
  public Result<?> deleteParty(@RequestParam Long partyId) {
    Assert.notNull(partyId, "部门ID不能为空");
    partyService.deleteParty(partyId);
    return Result.success();
  }
}
