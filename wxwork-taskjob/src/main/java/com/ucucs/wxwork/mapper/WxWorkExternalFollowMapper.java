package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkExternalFollow;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkExternalFollowMapper extends Serializable {
  int deleteByPrimaryKey(Long id);

  int insert(WxWorkExternalFollow record);

  int insertSelective(WxWorkExternalFollow record);

  WxWorkExternalFollow selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(WxWorkExternalFollow record);

  int updateByPrimaryKey(WxWorkExternalFollow record);
}
