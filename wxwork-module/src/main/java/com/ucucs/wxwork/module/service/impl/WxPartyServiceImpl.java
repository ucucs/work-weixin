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
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxPartyServiceImpl implements WxPartyService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CONTACT);
  }

  @Override
  public List<WxParty> list(Long partyId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("id", partyId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Department.DEPARTMENT_LIST, paramBuilder.build(), null, RequestType.GET);
    return WxParty.parseMsgBody(rspNode);
  }

  @Override
  public Long create(WxParty party) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Department.DEPARTMENT_CREATE,
            paramBuilder.build(),
            party.wrapMsgBody(),
            RequestType.POST_JSON);

    return rspNode.get("id").asLong();
  }

  @Override
  public void update(WxParty party) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    wxWorkService.getRsp(
        Department.DEPARTMENT_UPDATE,
        paramBuilder.build(),
        party.wrapMsgBody(),
        RequestType.POST_JSON);
  }

  @Override
  public void delete(Long partyId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("id", partyId).put("access_token", accessToken);

    wxWorkService.getRsp(Department.DEPARTMENT_DELETE, paramBuilder.build(), null, RequestType.GET);
  }
}
