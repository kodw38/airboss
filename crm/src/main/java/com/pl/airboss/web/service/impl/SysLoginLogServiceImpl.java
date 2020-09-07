package com.pl.airboss.web.service.impl;

import com.pl.airboss.utils.Convert;
import com.pl.airboss.web.bean.SecLoginLogBean;
import com.pl.airboss.web.dao.SecLoginLogBeanMapper;
import com.pl.airboss.web.service.ISysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 */
@Service
public class SysLoginLogServiceImpl implements ISysLoginLogService
{

    @Autowired
    private SecLoginLogBeanMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SecLoginLogBean logininfor)
    {
        logininforMapper.insert(logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SecLoginLogBean> selectLogininforList(SecLoginLogBean logininfor)
    {
            return logininforMapper.selectLoginLogList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids)
    {
        return logininforMapper.deleteLoginLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor()
    {
        //logininforMapper.cleanLogininfor();
    }
}
