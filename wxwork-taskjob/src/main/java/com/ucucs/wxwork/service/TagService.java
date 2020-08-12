package com.ucucs.wxwork.service;

import com.ucucs.wxwork.mapper.WxWorkGlobalTagMapper;
import com.ucucs.wxwork.model.WxWorkGlobalTag;
import com.ucucs.wxwork.module.entity.WxTag;
import com.ucucs.wxwork.module.service.WxTagService;
import com.ucucs.wxwork.module.util.BeanMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TagService {

  private final WxTagService wxTagService;
  private final WxWorkGlobalTagMapper wxWorkGlobalTagMapper;

  public List<WxTag> getTagList() {
    return wxTagService.list();
  }

  public void syncTag() {
    List<WxTag> tagList = getTagList();
    List<WxWorkGlobalTag> workTagList = BeanMapper.mapperList(tagList, WxWorkGlobalTag.class);
    WxWorkGlobalTag dbTag;
    for (WxWorkGlobalTag workTag : workTagList) {
      dbTag = wxWorkGlobalTagMapper.selectByPrimaryKey(workTag.getId());
      if (dbTag != null) {
        wxWorkGlobalTagMapper.updateByPrimaryKeySelective(workTag);
        continue;
      }

      wxWorkGlobalTagMapper.insertSelective(workTag);
    }
  }
}
