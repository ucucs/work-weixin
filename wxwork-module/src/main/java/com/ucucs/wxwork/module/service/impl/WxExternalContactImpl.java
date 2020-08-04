package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts.ExternalContact;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.ArrayUtil;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
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
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("page_id", pageIndex).put("page_size", pageSize);

    JsonNode rspNode =
        wxWorkService.getRsp(
            ExternalContact.LIST_UNASSIGNED_CONTACT,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);

    return WxExternalUnAssignPage.parseMsgBody(rspNode);
  }

  @Override
  public List<GroupChatStatus> listGroupChat(
      Integer pageIndex, Integer pageSize, Integer status, String[] userIds, String[] partyIds) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("status_filter", status).put("offset", pageIndex).put("limit", pageSize);

    if (!ArrayUtil.isEmpty(userIds) || !ArrayUtil.isEmpty(partyIds)) {
      MapBuilder<String, Object> ownerBuilder = new MapBuilder<>();
      ownerBuilder.put("userid_list", userIds).put("partyid_list", partyIds);
      paramBuilder.put("owner_filter", ownerBuilder.build());
    }

    JsonNode rspNode =
        wxWorkService.getRsp(
            ExternalContact.GROUP_CHAT_LIST,
            paramBuilder.build(),
            bodyBuilder.build(),
            RequestType.POST_JSON);

    return GroupChatStatus.parseMsgBody(rspNode);
  }

  @Override
  public void transferContact(String externalUserId, String handOverUserId, String takeOverUserId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder
        .put("external_userid", externalUserId)
        .put("handover_userid", handOverUserId)
        .put("takeover_userid", takeOverUserId);

    wxWorkService.getRsp(
        ExternalContact.TRANSFER_UNASSIGNED_CONTACT,
        paramBuilder.build(),
        bodyBuilder.build(),
        RequestType.POST_JSON);
  }

  @Override
  public void transferGroupChat(String[] groupChatIds, String takeOverUserId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    MapBuilder<String, Object> bodyBuilder = new MapBuilder<>();
    paramBuilder.put("chat_id_list", groupChatIds).put("new_owner", takeOverUserId);

    wxWorkService.getRsp(
        ExternalContact.TRANSFER_UNASSIGNED_GROUP_CHAT,
        paramBuilder.build(),
        bodyBuilder.build(),
        RequestType.POST_JSON);
  }

  @Override
  public WxExternalContactDetail getContactDetail(String externalUserId) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("external_userid", externalUserId).put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            ExternalContact.GET_CONTACT_DETAIL, paramBuilder.build(), null, RequestType.GET);
    return WxExternalContactDetail.parseMsgBody(rspNode);
  }
}
