package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.WxTag;
import com.ucucs.wxwork.module.entity.wrap.TagOperateResult;
import com.ucucs.wxwork.module.entity.wrap.TagUserInfo;
import com.ucucs.wxwork.module.service.WxTagService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TagService {

  private final WxTagService wxTagService;

  public List<WxTag> list() {
    return wxTagService.list();
  }

  public Long create(Long tagId, String tagName) {
    return wxTagService.create(tagId, tagName);
  }

  public void update(Long tagId, String tagName) {
    wxTagService.update(tagId, tagName);
  }

  public void delete(Long tagId) {
    wxTagService.delete(tagId);
  }

  public TagUserInfo listUsers(Long tagId) {
    return wxTagService.listUsers(tagId);
  }

  public TagOperateResult addUserToTag(Long tagId, List<String> userIds, List<String> partyIds) {
    return wxTagService.addUserToTag(tagId, userIds, partyIds);
  }

  public TagOperateResult removeUserFromTag(
      Long tagId, List<String> userIds, List<String> partyIds) {
    return wxTagService.removeUserFromTag(tagId, userIds, partyIds);
  }
}
