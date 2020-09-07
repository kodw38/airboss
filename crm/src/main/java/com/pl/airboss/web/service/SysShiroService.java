package com.pl.airboss.web.service;

import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.bean.OnlineSession;
import com.pl.airboss.web.bean.SecLoginOnlineBean;
import com.pl.airboss.web.dao.SecLoginOnlineBeanMapper;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *@Description 会话db操作处理
 *@auther Kod Wong
 *@Date 2020/4/28 10:07
 *@Param
 *@return
 *@Version 1.0
 */
@Component
public class SysShiroService
{
    @Autowired
    private SecLoginOnlineBeanMapper loginOnlineBeanMapper;

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession)
    {
        loginOnlineBeanMapper.deleteByPrimaryKey(String.valueOf(onlineSession.getId()));
    }

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId)
    {
        SecLoginOnlineBean userOnline = loginOnlineBeanMapper.selectByPrimaryKey(String.valueOf(sessionId));
        return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
    }

    public Session createSession(SecLoginOnlineBean userOnline)
    {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtils.isNotNull(userOnline))
        {
            onlineSession.setId(userOnline.getSessionId());
            onlineSession.setHost(userOnline.getIpAddress());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setDeptName(null);
            onlineSession.setLoginName(userOnline.getLoginCode());
            onlineSession.setStartTimestamp(userOnline.getLoginDate());
            onlineSession.setLastAccessTime(userOnline.getLastAccessDate());
            onlineSession.setTimeout(userOnline.getExpireeTime());
        }
        return onlineSession;
    }
}
