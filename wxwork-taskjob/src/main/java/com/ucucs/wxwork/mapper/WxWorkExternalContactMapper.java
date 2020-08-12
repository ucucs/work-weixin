package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkExternalContact;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkExternalContactMapper extends Serializable {
  int deleteByPrimaryKey(String externalUserId);

  int insert(WxWorkExternalContact record);

  int insertSelective(WxWorkExternalContact record);

  WxWorkExternalContact selectByPrimaryKey(String externalUserId);

  int updateByPrimaryKeySelective(WxWorkExternalContact record);

  int updateByPrimaryKey(WxWorkExternalContact record);
}
