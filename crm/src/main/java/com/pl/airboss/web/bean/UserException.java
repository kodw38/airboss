package com.pl.airboss.web.bean;


import com.pl.airboss.framework.bean.BaseException;

/**
 * 用户信息异常类
 * 
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
