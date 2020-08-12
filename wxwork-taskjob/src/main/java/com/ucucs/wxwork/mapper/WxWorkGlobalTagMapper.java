package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkGlobalTag;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkGlobalTagMapper extends Serializable {
  int deleteByPrimaryKey(Long id);

  int insert(WxWorkGlobalTag record);

  int insertSelective(WxWorkGlobalTag record);

  WxWorkGlobalTag selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(WxWorkGlobalTag record);

  int updateByPrimaryKey(WxWorkGlobalTag record);
}
