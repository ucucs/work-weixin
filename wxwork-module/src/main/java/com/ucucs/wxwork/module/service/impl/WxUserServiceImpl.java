package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts.User;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.entity.WxUser;
import com.ucucs.wxwork.module.entity.WxUserDetail;
import com.ucucs.wxwork.module.service.WxUserService;
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
public class WxUserServiceImpl implements WxUserService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CONTACT);
  }

  @Override
  public List<WxUserDetail> list(Long partyId, Boolean fetchChild) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder
        .put("department_id", partyId)
        .put("fetch_child", fetchChild ? 1 : 0)
        .put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(User.USER_LIST, paramBuilder.build(), null, RequestType.GET);
    return WxUserDetail.parseMsgBody(rspNode);
  }

  @Override
  public List<UserSimple> listSimple(Long partyId, Boolean fetchChild) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder
        .put("department_id", partyId)
        .put("fetch_child", fetchChild ? 1 : 0)
        .put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(User.USER_SIMPLE_LIST, paramBuilder.build(), null, RequestType.GET);
    return UserSimple.parseMsgBody(rspNode);
  }

  @Override
  public void create(WxUser user) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    wxWorkService.getRsp(
        User.USER_CREATE, paramBuilder.build(), user.wrapMsgBody(), RequestType.POST_JSON);
  }

  @Override
  public void update(WxUser user) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    wxWorkService.getRsp(
        User.USER_UPDATE, paramBuilder.build(), user.wrapMsgBody(), RequestType.POST_JSON);
  }

  @Override
  public void delete(String... userIds) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    if (userIds.length == 1) {
      paramBuilder.put("userid", userIds[0]);
      wxWorkService.getRsp(User.USER_DELETE, paramBuilder.build(), null, RequestType.GET);
      return;
    }

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    bodyBuilder.put("useridlist", userIds);
    wxWorkService.getRsp(
        User.USER_BATCH_DELETE, paramBuilder.build(), bodyBuilder.build(), RequestType.POST_JSON);
  }

  @Override
  public WxUser getById(String userId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("userid", userId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(User.USER_GET, paramBuilder.build(), null, RequestType.GET);
    return WxUser.parseSinge(rspNode);
  }

  @Override
  public String userIdToOpenId(String userId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    bodyBuilder.put("userid", userId);

    JsonNode rspNode =
        wxWorkService.getRsp(
            User.USER_CONVERT_TO_OPENID,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);

    return rspNode.get("openid").asText();
  }

  @Override
  public String openIdToUserId(String openId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    bodyBuilder.put("openid", openId);

    JsonNode rspNode =
        wxWorkService.getRsp(
            User.USER_CONVERT_TO_USERID,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);

    return rspNode.get("userid").asText();
  }
}
