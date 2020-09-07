package com.pl.airboss.web.service;


import com.pl.airboss.web.bean.SecOpLogBean;

import java.util.Date;
import java.util.List;

/**
 * 操作日志 服务层
 * 
 */
public interface ISysOperLogService
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    public void insertOperlog(SecOpLogBean operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<SecOpLogBean> selectOperLogList(SecOpLogBean operLog);

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteOperLogByIds(String ids,Date doneDate);

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public SecOpLogBean selectOperLogById(Integer operId,Date doneDate);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
