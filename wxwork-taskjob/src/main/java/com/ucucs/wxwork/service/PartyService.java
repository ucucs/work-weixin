package com.ucucs.wxwork.service;

import com.ucucs.wxwork.mapper.WxWorkPartyMapper;
import com.ucucs.wxwork.model.WxWorkParty;
import com.ucucs.wxwork.module.entity.WxParty;
import com.ucucs.wxwork.module.service.WxPartyService;
import com.ucucs.wxwork.module.util.BeanMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class PartyService {

  private final WxPartyService wxPartyService;

  private final WxWorkPartyMapper wxWorkPartyMapper;

  public List<WxParty> getPartyList(Long partyId) {
    return wxPartyService.list(partyId);
  }

  public void syncParty() {
    List<WxParty> partyList = getPartyList(0L);
    List<WxWorkParty> workPartyList = BeanMapper.mapperList(partyList, WxWorkParty.class);

    WxWorkParty dbParty;
    for (WxWorkParty workParty : workPartyList) {
      dbParty = wxWorkPartyMapper.selectByPrimaryKey(workParty.getId());
      if (dbParty != null) {
        wxWorkPartyMapper.updateByPrimaryKeySelective(workParty);
        continue;
      }

      wxWorkPartyMapper.insertSelective(workParty);
    }
  }
}
