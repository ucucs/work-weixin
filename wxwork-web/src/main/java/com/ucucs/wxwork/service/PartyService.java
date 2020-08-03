package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.WxParty;
import com.ucucs.wxwork.module.service.WxPartyService;
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

  public List<WxParty> getPartyList(Long partyId) {
    return wxPartyService.list(partyId);
  }

  public Long createParty(WxParty wxParty) {
    return wxPartyService.create(wxParty);
  }

  public void updateParty(WxParty wxParty) {
    wxPartyService.update(wxParty);
  }

  public void deleteParty(Long partyId) {
    wxPartyService.delete(partyId);
  }
}
