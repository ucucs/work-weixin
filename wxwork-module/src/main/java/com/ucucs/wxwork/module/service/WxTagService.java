package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.WxTag;
import com.ucucs.wxwork.module.entity.wrap.TagOperateResult;
import com.ucucs.wxwork.module.entity.wrap.TagUserInfo;
import java.util.List;

public interface WxTagService {

  List<WxTag> list();

  Long create(Long tagId, String tagName);

  void update(Long tagId, String tagName);

  void delete(Long tagId);

  TagUserInfo listUsers(Long tagId);

  TagOperateResult addUserToTag(Long tagId, List<String> userIds, List<String> partyIds);

  TagOperateResult removeUserFromTag(Long tagId, List<String> userIds, List<String> partyIds);
}
