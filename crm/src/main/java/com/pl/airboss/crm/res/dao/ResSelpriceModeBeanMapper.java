package com.pl.airboss.crm.res.dao;

import com.pl.airboss.crm.res.bean.ResSelpriceModeBean;
import com.pl.airboss.crm.res.bean.ResSelpriceModeBeanKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSelpriceModeBeanMapper {
    int deleteByPrimaryKey(Long key);

    int insert(ResSelpriceModeBean record);

    int insertSelective(ResSelpriceModeBean record);

    ResSelpriceModeBean selectByPrimaryKey(Long key);

    int updateByPrimaryKeySelective(ResSelpriceModeBean record);

    int updateByPrimaryKey(ResSelpriceModeBean record);

    List<ResSelpriceModeBean> selectList(ResSelpriceModeBean bean);

    boolean checkPatternPriceNameUnique(@Param("modeDesc") String modeDesc, @Param("resSpecId") Long resSpecId);
}