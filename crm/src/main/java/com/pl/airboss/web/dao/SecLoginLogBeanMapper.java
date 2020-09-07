package com.pl.airboss.web.dao;

import com.pl.airboss.framework.mybatisplugin.TableSplit;
import com.pl.airboss.framework.mybatisplugin.YYYYMMTableSplitStrategy;
import com.pl.airboss.web.bean.SecLoginLogBeanExample;
import com.pl.airboss.web.bean.SecLoginLogBean;

import java.util.List;

@TableSplit(splitStrategy=YYYYMMTableSplitStrategy.class)
public interface SecLoginLogBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecLoginLogBean record);

    int insertSelective(SecLoginLogBean record);

    List<SecLoginLogBean> selectByExample(SecLoginLogBeanExample example);

    SecLoginLogBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecLoginLogBean record);

    int updateByPrimaryKey(SecLoginLogBean record);

    List<SecLoginLogBean> selectLoginLogList(SecLoginLogBean record);

    int deleteLoginLogByIds(String[] ids);
}