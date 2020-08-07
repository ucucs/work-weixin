package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkUserParty;
import com.ucucs.wxwork.model.WxWorkUserPartyKey;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkUserPartyMapper extends Serializable {
    int deleteByPrimaryKey(WxWorkUserPartyKey key);

    int insert(WxWorkUserParty record);

    int insertSelective(WxWorkUserParty record);

    WxWorkUserParty selectByPrimaryKey(WxWorkUserPartyKey key);

    int updateByPrimaryKeySelective(WxWorkUserParty record);

    int updateByPrimaryKey(WxWorkUserParty record);
}