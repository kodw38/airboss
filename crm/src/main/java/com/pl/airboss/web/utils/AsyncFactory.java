package com.pl.airboss.web.utils;

import com.pl.airboss.utils.*;
import com.pl.airboss.utils.*;
import com.pl.airboss.web.bean.SecLoginLogBean;
import com.pl.airboss.web.bean.SecLoginOnlineBean;
import com.pl.airboss.web.bean.SecOpLogBean;
import com.pl.airboss.web.dao.SecLoginLogBeanMapper;
import com.pl.airboss.web.dao.SecLoginOnlineBeanMapper;
import com.pl.airboss.web.dao.SecOpLogBeanMapper;
import com.pl.airboss.web.Constants;
import com.pl.airboss.web.bean.OnlineSession;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * 
 * @author liuhulu
 *
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 同步session到数据库
     * 
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                SecLoginOnlineBean online = new SecLoginOnlineBean();
                online.setSessionId(String.valueOf(session.getId()));
                online.setLoginCode(session.getLoginName());
                online.setLoginDate(session.getStartTimestamp());
                online.setLastAccessDate(session.getLastAccessTime());
                online.setExpireeTime(((Long)(session.getTimeout())).intValue());
                online.setIpAddress(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setState(session.getStatus().name());
                SpringUtils.getBean(SecLoginOnlineBeanMapper.class).saveOnline(online);

            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SecOpLogBean operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                operLog.setDoneDate(new Date());
                SpringUtils.getBean(SecOpLogBeanMapper.class).insert(operLog);
            }
        };
    }

    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SecLoginLogBean logininfor = new SecLoginLogBean();
                logininfor.setLoginCode(username);
                logininfor.setIpAddress(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setLoginDate(new Date());
                logininfor.setRemark(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SecLoginLogBeanMapper.class).insert(logininfor);
            }
        };
    }
}
