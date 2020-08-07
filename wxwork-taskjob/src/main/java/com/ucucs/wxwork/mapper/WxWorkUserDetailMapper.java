package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkUserDetail;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkUserDetailMapper extends Serializable {
    int deleteByPrimaryKey(String userId);

    int insert(WxWorkUserDetail record);

    int insertSelective(WxWorkUserDetail record);

    WxWorkUserDetail selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(WxWorkUserDetail record);

    int updateByPrimaryKey(WxWorkUserDetail record);
}