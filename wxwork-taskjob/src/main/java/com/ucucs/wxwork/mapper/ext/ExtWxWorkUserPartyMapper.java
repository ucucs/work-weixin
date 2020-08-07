package com.ucucs.wxwork.mapper.ext;

import com.ucucs.wxwork.mapper.WxWorkUserPartyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExtWxWorkUserPartyMapper extends WxWorkUserPartyMapper {
  int deleteByUserId(@Param("userId") String userId);
}
