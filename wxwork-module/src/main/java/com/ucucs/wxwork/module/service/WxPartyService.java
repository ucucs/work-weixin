package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.WxParty;
import java.util.List;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxPartyService {

  List<WxParty> list(Long partyId);

  Long create(WxParty party);

  void update(WxParty party);

  void delete(Long partyId);
}
