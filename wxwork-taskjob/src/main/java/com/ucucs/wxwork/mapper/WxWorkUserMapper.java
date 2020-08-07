package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkUser;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkUserMapper extends Serializable {
    int deleteByPrimaryKey(String userId);

    int insert(WxWorkUser record);

    int insertSelective(WxWorkUser record);

    WxWorkUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(WxWorkUser record);

    int updateByPrimaryKey(WxWorkUser record);
}