package com.pl.airboss.crm.utils;/**
 * Created by admin on 2020/9/9.
 */

import com.pl.airboss.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Utils
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/9 14:30
 * @Version 1.0
 **/
public class Utils {

    /**
     *根据正则表达式生成列表'82233[1-9][1-9]{3}'
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 14:30
     *@Param
     *@return
     *@Version 1.0
     */
    static public List<String> getListByRegexp(String regexp) throws Exception{
        if(StringUtils.isNotEmpty(regexp)){
            StringBuffer sb = new StringBuffer();
            boolean startrange=false;
            boolean startrepeat=false;
            List<String> ret = new ArrayList();
            StringBuilder prerag = new StringBuilder();
            StringBuilder prerepeat = new StringBuilder();
            for(int i=0;i<regexp.length();i++){
                char c = regexp.charAt(i);
                if(startrepeat && c=='}'){
                    startrepeat=false;
                    if(prerepeat.length()==1 && prerepeat.charAt(0)>='0' && prerepeat.charAt(0)<='9'){
                        if(prerag.length()>0){
                            int n = prerag.charAt(0)-48;
                            int e = prerag.charAt(2)-48;
                            if(n>e){
                                n = prerag.charAt(2)-48;
                                e = prerag.charAt(0)-48;
                            }
                            int cn = prerepeat.charAt(0)-48;
                            for(int j=0;j<cn;j++){
                                appendList(ret,n,e);//笛卡尔集
                            }
                        }
                    }else{
                        throw new Exception(regexp+"不合规则，规则如样例:82233[1-9][1-9]{3}");
                    }
                }else if(startrange && c==']'){
                    startrange=false;
                    if(prerag.length()>0 && prerag.length()==3 && prerag.charAt(1)=='-' && prerag.charAt(0)>='0' && prerag.charAt(0)<='9' && prerag.charAt(2)>='0' && prerag.charAt(2)<='9'){
                        int n = prerag.charAt(0)-48;
                        int e = prerag.charAt(2)-48;
                        if(n>e){
                            n = prerag.charAt(2)-48;
                            e = prerag.charAt(0)-48;
                        }
                        if(ret.size()==0){
                            ret.add(sb.toString());
                        }
                        appendList(ret,n,e);//笛卡尔集
                    }else{
                        throw new Exception(regexp+"不合规则，规则如样例:82233[1-9][1-9]{3}");
                    }
                    //prerag.delete(0,prerag.length());
                } else if(startrange) {
                    prerag.append(c);
                    continue;
                }else if(startrepeat) {
                    prerepeat.append(c);
                    continue;
                }else if(c!='[' && c!=']' && c!='-' && c!='{' && c!='}' && c>='0' && c<='9') {
                    sb.append(c);
                }else if(c=='['){
                    startrange=true;
                    prerag.delete(0,prerag.length());
                }else if(c=='{'){
                    startrepeat=true;
                    prerepeat.delete(0,prerepeat.length());
                }else{
                    throw new Exception("不支持的号段表达式，规则如样例:82233[1-9][1-9]{3}");
                }
            }

            if(ret.size()==0 && sb.length()>0){
                ret.add(sb.toString());
            }
            return ret;

        }
        return null;
    }
    static void appendList(List<String> ls,int start,int end){
        List temp = new ArrayList();
        for(String s:ls){
            for(int i=start;i<=end;i++){
                temp.add(s+i);
            }
        }
        ls.clear();
        ls.addAll(temp);
    }
    public static void main(String[] args){
        try{
            System.out.println('0');
            System.out.println('9');
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
