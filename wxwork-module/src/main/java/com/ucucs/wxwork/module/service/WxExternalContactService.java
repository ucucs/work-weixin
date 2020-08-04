package com.ucucs.wxwork.module.service;

<<<<<<< HEAD
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
=======
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import java.util.List;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxExternalContactService {

  List<String> list(String userId);

  WxExternalUnAssignPage listUnAssigned(Integer pageIndex, Integer pageSize);
<<<<<<< HEAD

  List<GroupChatStatus> listGroupChat(
      Integer pageIndex, Integer pageSize, Integer status, String[] userIds, String[] partyIds);

  void transferContact(String externalUserId, String handOverUserId, String takeOverUserId);

  void transferGroupChat(String[] groupChatIds, String takeOverUserId);

  WxExternalContactDetail getContactDetail(String externalUserId);
=======
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b
}
