package com.pl.airboss.web.dao;

import com.pl.airboss.framework.mybatisplugin.TableSplit;
import com.pl.airboss.framework.mybatisplugin.YYYYMMTableSplitStrategy;
import com.pl.airboss.web.bean.SecOpLogBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@TableSplit(splitStrategy=YYYYMMTableSplitStrategy.class)
public interface SecOpLogBeanMapper {
    int deleteByPrimaryKey(@Param("recId") Integer recId,@Param("doneDate") Date doneDate);

    int insert(SecOpLogBean bean);

    int insertSelective(SecOpLogBean record);

    SecOpLogBean selectByPrimaryKey(@Param("recId") Integer recId,@Param("doneDate") Date doneDate);

    int updateByPrimaryKeySelective(SecOpLogBean record);

    int updateByPrimaryKey(SecOpLogBean record);

    List<SecOpLogBean> selectOperLogList(SecOpLogBean operLog);

    int deleteOperLogByIds(@Param("operId") String[] ids,@Param("doneDate") Date doneDate);
}