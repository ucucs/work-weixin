package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkExternalFollowTag;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkExternalFollowTagMapper extends Serializable {
  int deleteByPrimaryKey(Long id);

  int insert(WxWorkExternalFollowTag record);

  int insertSelective(WxWorkExternalFollowTag record);

  WxWorkExternalFollowTag selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(WxWorkExternalFollowTag record);

  int updateByPrimaryKey(WxWorkExternalFollowTag record);
}
