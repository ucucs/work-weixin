package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts.ExternalContact;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import com.ucucs.wxwork.module.service.WxExternalContactService;
import com.ucucs.wxwork.module.service.WxWorkService;
import com.ucucs.wxwork.module.util.JsonUtil;
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
public class WxExternalContactImpl implements WxExternalContactService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CONTACT);
  }

  @Override
  public List<String> list(String userId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("userid", userId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            ExternalContact.LIST_EXTERNAL_CONTACT, paramBuilder.build(), null, RequestType.GET);
    return JsonUtil.nodeToBeanList(rspNode.withArray("external_userid"), String.class);
  }

  @Override
  public WxExternalUnAssignPage listUnAssigned(Integer pageIndex, Integer pageSize) {
    return null;
  }
}
