package com.pl.airboss.web.bean;

/**
 * 用户锁定异常类
 * 
 * @author pl
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
