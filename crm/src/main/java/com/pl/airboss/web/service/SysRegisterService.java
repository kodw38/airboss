package com.pl.airboss.web.service;

import com.pl.airboss.framework.utils.AsyncManager;
import com.pl.airboss.utils.ServletUtils;
import com.pl.airboss.web.utils.AsyncFactory;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.dao.SecOperatorBeanMapper;
import com.pl.airboss.utils.MessageUtils;
import com.pl.airboss.web.Constants;
import com.pl.airboss.web.bean.ShiroConstants;
import com.pl.airboss.web.bean.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注册校验方法
 * 
 */
@Component
public class SysRegisterService
{
    @Autowired
    private SecOperatorBeanMapper userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 注册
     */
    public String register(SecOperatorBean user)
    {
        String msg = "", username = user.getLoginCode(), password = user.getLoginPwd();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            //user.setSalt(ShiroUtils.randomSalt());
            user.setLoginPwd(passwordService.encryptPassword(user.getLoginCode(), user.getLoginPwd(), Constants.SALT));
            int regFlag = userService.insert(user);
            if (regFlag<1)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
