package com.pl.airboss.framework.file;


import com.pl.airboss.framework.bean.BaseException;

/**
 * 文件信息异常类
 * 
 * @author pl
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
