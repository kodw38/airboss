package com.pl.airboss.web.service.impl;

import com.pl.airboss.utils.Convert;
import com.pl.airboss.web.bean.SecOpLogBean;
import com.pl.airboss.web.service.ISysOperLogService;
import com.pl.airboss.web.dao.SecOpLogBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 操作日志 服务层处理
 * 
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SecOpLogBeanMapper operLogMapper;

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SecOpLogBean operLog)
    {
        operLog.setDoneDate(new Date());
        operLogMapper.insert(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SecOpLogBean> selectOperLogList(SecOpLogBean operLog)
    {
        return operLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids,Date doneDate)
    {
        return operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids),doneDate);
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SecOpLogBean selectOperLogById(Integer operId,Date doneDate)
    {
        return operLogMapper.selectByPrimaryKey(operId,doneDate);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        //operLogMapper.cleanOperLog();
    }
}
