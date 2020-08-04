package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts.Tag;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.WxTag;
import com.ucucs.wxwork.module.entity.wrap.TagOperateResult;
import com.ucucs.wxwork.module.entity.wrap.TagUserInfo;
import com.ucucs.wxwork.module.service.WxTagService;
import com.ucucs.wxwork.module.service.WxWorkService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WxTagServiceImpl implements WxTagService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CONTACT);
  }

  @Override
  public List<WxTag> list() {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(Tag.TAG_LIST, paramBuilder.build(), null, RequestType.GET);
    return WxTag.parseMsgBody(rspNode);
  }

  @Override
  public Long create(Long tagId, String tagName) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("tagname", tagName);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Tag.TAG_CREATE, paramBuilder.build(), bodyBuilder.build(), RequestType.POST_JSON);
    return rspNode.get("tagid").asLong();
  }

  @Override
  public void update(Long tagId, String tagName) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("tagname", tagName);

    wxWorkService.getRsp(
        Tag.TAG_UPDATE, paramBuilder.build(), bodyBuilder.build(), RequestType.POST_JSON);
  }

  @Override
  public void delete(Long tagId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("access_token", accessToken);

    wxWorkService.getRsp(Tag.TAG_DELETE, paramBuilder.build(), null, RequestType.GET);
  }

  @Override
  public TagUserInfo listUsers(Long tagId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(Tag.TAG_GET, paramBuilder.build(), null, RequestType.GET);
    return TagUserInfo.parseMsgBody(rspNode);
  }

  @Override
  public TagOperateResult addUserToTag(Long tagId, List<String> userIds, List<String> partyIds) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("userlist", userIds).put("partylist", partyIds);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Tag.TAG_ADD_TAG_USERS,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);
    return TagOperateResult.parseMsgBody(rspNode);
  }

  @Override
  public TagOperateResult removeUserFromTag(
      Long tagId, List<String> userIds, List<String> partyIds) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("tagid", tagId).put("userlist", userIds).put("partylist", partyIds);

    JsonNode rspNode =
        wxWorkService.getRsp(
            Tag.TAG_DEL_TAG_USERS,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);
    return TagOperateResult.parseMsgBody(rspNode);
  }
}
