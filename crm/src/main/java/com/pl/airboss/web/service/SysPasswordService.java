package com.pl.airboss.web.service;

import com.pl.airboss.framework.utils.AsyncManager;
import com.pl.airboss.utils.MessageUtils;
import com.pl.airboss.web.Constants;
import com.pl.airboss.web.bean.UserPasswordNotMatchException;
import com.pl.airboss.web.utils.AsyncFactory;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.bean.ShiroConstants;
import com.pl.airboss.web.bean.UserPasswordRetryLimitExceedException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 * 
 */
@Component
public class SysPasswordService
{
    @Autowired
    private EhCacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    public void validate(SecOperatorBean user, String password)
    {
        String loginName = user.getLoginCode();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SecOperatorBean user, String newPassword)
    {
        return user.getLoginPwd().equals(encryptPassword(user.getLoginCode(), newPassword, Constants.SALT));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return new Md5Hash(username + password + salt).toHex();
    }

    public void unlock(String loginName){
        loginRecordCache.remove(loginName);
    }

}
