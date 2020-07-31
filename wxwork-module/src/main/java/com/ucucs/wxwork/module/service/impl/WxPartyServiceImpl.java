package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts.Department;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.WxParty;
import com.ucucs.wxwork.module.service.WxPartyService;
import com.ucucs.wxwork.module.service.WxWorkService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxPartyServiceImpl implements WxPartyService {

  private static final Logger LOG = LoggerFactory.getLogger(WxPartyServiceImpl.class);

  private final WxWorkService wxWorkService;

  @Override
  public List<WxParty> list(Long partyId) {
    String accessToken = wxWorkService.getAccessToken(TokenType.CONTACT);

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("id", partyId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Department.DEPARTMENT_LIST, paramBuilder.build(), null, RequestType.GET);
    return WxParty.parseMsgBody(rspNode);
  }

  @Override
  public Long create(WxParty party) {
    return null;
  }

  @Override
  public void update(WxParty party) {}

  @Override
  public void delete(Long partyId) {}
}
