package com.pl.airboss.app.utils;

/**
 * Created by admin on 2020/4/12.
 */
public class StringUtils {


    public StringUtils(){

    }
    public void test(){
        System.out.println("1111");
    }
    /**
     * 判断txt中是否有keys中的字符串，如果有返回ture
     * @param txt
     * @param keys
     * @return
     */
    public boolean isContains(String txt,String[] keys){
        if(null == txt || null == keys)return false;
        for(String key:keys){
            if(txt.indexOf(key)>=0){
                return true;
            }
        }
        return false;
    }
}

