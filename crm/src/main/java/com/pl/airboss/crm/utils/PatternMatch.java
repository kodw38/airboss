package com.pl.airboss.crm.utils;/**
 * Created by admin on 2020/9/9.
 */

import com.pl.airboss.crm.res.bean.Arithmetic;
import com.pl.airboss.crm.res.bean.Queue;

import java.util.List;
import java.util.StringTokenizer;

/**
 * @ClassName PatternMatch
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/9 16:54
 * @Version 1.0
 **/
public class PatternMatch {

    public static boolean matchExp(String billId, String exp, String regionId) throws Exception
    {
        exp = PatternMatch.preProcessExp(billId, exp, regionId);
        // 预处理表达式后进行分支处理：两种不同风格的表达式类型
        if (PatternMatch.isSpecialExp(exp)) // 包含 )&&( 或 )||( 的属于specialExp
        {
            return  ExtPatternMatch.processExp(billId, exp);
        } else
            return PatternMatch.processExp(billId, exp);
    }
    public static boolean isSpecialExp(String exp) throws Exception
    {
        return exp.contains(")&&(") || exp.contains(")||(");
    }
    public static String transform(String exp, String regionId)
    {
        String str = exp.replace("@bill_id", "v");
        str = str.replace("@region_id", regionId);
        str = str.replace("$substr", "S");
        str = str.replace("$length", "L");
        str = str.replace("$contain", "C");
        return str;
    }
    public static String preProcessExp(String billId, String exp, String regionId) throws Exception
    {
        String newExp = PatternMatch.transform(exp, regionId);
        return newExp;
    }
    /**
     * 处理表达式 S(v,L(v)-6,1) == '6'|| S(v,L(v)-6,1) == '8'|| S(v,L(v)-6,1) == '9'
     *
     * @param billId
     * @param exp
     * @return
     * @throws Exception
     */
    public static boolean processExp(String billId, String exp) throws Exception
    {
        Queue queue = PatternMatch.splitExp(exp);
        boolean flag = false;
        boolean flag2 = false;

        boolean rst = false;
        for (int i = 0; i < queue.size(); i++)
        {
            String temp = (String) queue.get(i);
            if (temp.startsWith("("))// 如果表达式带括号则需要预处理
                temp = PatternMatch.transfromExp(temp);// 预处理子表达式（去掉左右括号）
            if (temp.startsWith("S") || temp.startsWith("C"))
            {
                flag = PatternMatch.processSubExp(billId, temp);
                rst = flag;
            } else if ("||".equals(temp))
            {
                i = i + 1; // 遇到运算符将下一元素出队，参与运算
                temp = (String) queue.get(i);
                if (temp.startsWith("("))// 如果表达式带括号则需要预处理
                    temp = PatternMatch.transfromExp(temp);// 预处理子表达式
                flag2 = PatternMatch.processSubExp(billId, temp);
                rst = flag == true || flag2 == true;
                flag = rst;

            } else if ("&&".equals(temp))
            {
                temp = (String) queue.get(i + 1);
                if (temp.startsWith("("))// 如果表达式带括号则需要预处理
                    temp = PatternMatch.transfromExp(temp);// 预处理子表达式
                flag2 = PatternMatch.processSubExp(billId, temp);
                rst = flag == true && flag2 == true;
                flag = rst;
                i = i + 1;
            }
        }
        return rst;
    }

    /**
     * 将表达式以 || 和 && 为界限分割成 Queue 队列
     *
     * @param exp
     * @return
     */
    public static Queue splitExp(String exp)
    {
        Queue queue = new Queue();
        // 首先对表达式进行分割，第一次采用"||"分割，确保分割后每段都没有"||"
        String[] firstSplit = PatternMatch.split(exp, "||");

        for (int i = 0; i < firstSplit.length; i++)
        {
            // 其次对分割后表达式再进行分割，第二次采用"&&"分割，确保分割后每段都没有"&&"
            String[] secendSplit = PatternMatch.split(firstSplit[i], "&&");
            int size = secendSplit.length;
            if (secendSplit.length > 1)
            {
                for (int j = 0; j < size; j++)
                {
                    queue.put(secendSplit[j].trim());
                    if (j < size - 1)
                        queue.put("&&");
                }
            } else
            {
                queue.put(firstSplit[i].trim());
            }

            if (i < firstSplit.length - 1)
                queue.put("||");
        }
        return queue;
    }

    // 表达式分为2种:带左右括号和不带左右括号；表达式约束：同一表达式逻辑运算符(逻辑或||、逻辑与&&)两侧要么都带，要么都不带括号
    /**
     * 去掉表达式两端的括号
     *
     * @param exp
     * @return
     */
    public static String transfromExp(String exp)
    {
        // 去掉左括号 (S(v,L(v)-4,1)=='4'---------S(v,L(v)-4,1)=='4'
        if (exp.startsWith("("))
        {
            exp = exp.substring(1, exp.length());
        }

        // 去掉右括号 S(v,L(v)-4,1)=='4')---------S(v,L(v)-4,1)=='4'
        if (exp.endsWith(")"))
        {
            exp = exp.substring(0, exp.length() - 1);
        }
        return exp;
    }

    public static String[] split(String str, String splitChar)
    {
        StringTokenizer st = new StringTokenizer("");
        if ("||".equals(splitChar))
        {
            st = new StringTokenizer(str, "||");
        } else if ("&&".equals(splitChar))
        {
            st = new StringTokenizer(str, "&&");
        } else if (",".equals(splitChar))
        {
            st = new StringTokenizer(str, ",");
        } else if ("==".equals(splitChar))
        {
            st = new StringTokenizer(str, "==");
        } else if ("!=".equals(splitChar))
        {
            st = new StringTokenizer(str, "!=");
        }

        String[] temp = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens())
        {
            temp[i] = st.nextToken();
            i++;
        }
        return temp;
    }

    /**
     * 处理带有==和!=的表达式
     *
     * @param billId
     * @param exp
     * @return
     * @throws Exception
     */
    public static boolean processSubExp(String billId, String exp) throws Exception
    {
        String[] expTemp = null;
        if (exp.contains("=="))
        {
            expTemp = PatternMatch.split(exp, "==");
            expTemp[0] = expTemp[0].trim();
            expTemp[1] = expTemp[1].trim();
            if (exp.startsWith("S"))
            {
                String rst = PatternMatch.processSubstr(billId, expTemp[0]); // 处理 $substr( , , ) S(v,L(v)-6,1) == '6'
                if (expTemp[1].startsWith("'"))
                {
                    expTemp[1] = expTemp[1].substring(1, expTemp[1].length() - 1);
                } else if (expTemp[1].startsWith("S"))
                {// (S(v,L(v)-4,1)!=S(v,L(v)-3,1))
                    // expTemp[1] = PatternMatch.transfromExp(expTemp[1]);//
                    // 去掉右括号
                    expTemp[1] = PatternMatch.processSubstr(billId, expTemp[1]);
                }
                if (rst.equals(expTemp[1]))
                    return true;
            } else if (exp.startsWith("C"))
            {
                boolean flag = PatternMatch.processContain(billId, expTemp[0]); // C(S(v,3,4),'4')==true
                return Boolean.toString(flag).equals(expTemp[1]);

            } else if (exp.startsWith("L"))
            {
                //log.error(".............");
            }
            return false;
        } else if (exp.contains("!="))
        {
            expTemp = PatternMatch.split(exp, "!=");
            expTemp[0] = expTemp[0].trim();
            expTemp[1] = expTemp[1].trim();
            if (exp.startsWith("S"))
            {// S(v,L(v)-6,1) == '6'
                String rst = PatternMatch.processSubstr(billId, expTemp[0]);
                if (expTemp[1].startsWith("'"))
                {
                    expTemp[1] = expTemp[1].substring(1, expTemp[1].length() - 1);
                } else if (expTemp[1].startsWith("S"))
                {// (S(v,L(v)-4,1)!=S(v,L(v)-3,1))
                    // expTemp[1] = PatternMatch.transfromExp(expTemp[1]);// 去掉右括号
                    expTemp[1] = PatternMatch.processSubstr(billId, expTemp[1]);
                }
                if (!rst.equals(expTemp[1]))
                    return true;
            } else if (exp.startsWith("C"))
            {// C(S(v,3,4),'4')==true
                boolean flag = PatternMatch.processContain(billId, expTemp[0]);
                return !Boolean.toString(flag).equals(expTemp[1]);

            } else if (exp.startsWith("L"))
            {
                //log.error(".............");
            }
            return false;
        }
        return false;
    }

    /**
     * 处理$contain ，目前第一个参数必须是S() ， 第二个是要判断的字符（串）
     *
     * @param billId
     *            电话号码
     * @param exp
     *            表达式
     * @return
     * @throws Exception
     */
    public static boolean processContain(String billId, String exp) throws Exception
    {
        // C(S(v,3,4),'4')
        boolean rst = false;
        char c = exp.charAt(2);
        if (c == 'S')
        {
            if (exp.contains("L(v)"))
            {
                // 需处理如下情况-C(S(v,L(v)-3,3),'4')--转换--C(S(v,3,3),'4')
                exp = PatternMatch.getSubStrExp(billId, exp);
            }

            int index = exp.indexOf(')');
            String temp = exp.substring(2, index + 1);// para: S(v,3,4)
            String substr = PatternMatch.processSubstr(billId, exp);
            exp = exp.replace(temp, substr);// C("5104",'4');
            String[] para = PatternMatch.split(exp, ",");
            para[1] = PatternMatch.getParaInt(para[1]);
            rst = PatternMatch.invokeContain(substr, para[1]);
        }

        return rst;
    }

    /**
     * 去掉单引号
     *
     * @param str
     * @return
     */
    public static String getParaInt(String str)
    {
        String temp = null;
        if (str.startsWith("'"))
        {
            temp = str.substring(1, str.length() - 2);
        } else
            temp = str.substring(0, str.length() - 1);

        return temp;
    }

    /**
     * 判断是否包含
     *
     * @param v
     * @param s
     * @return boolean
     */
    public static boolean contain(String v, String s)
    {
        return v.contains(s);
    }
    /**
     * 判断是否包含
     *


     * @return boolean
     */
    public static boolean invokeContain(String billId, String str) throws Exception
    {
        // C(v,'6')
        return PatternMatch.contain(billId, str);
    }

    /**
     * 处理C中包含的S(,,)
     *
     * @param billId
     * @param exp
     * @return
     * @throws Exception
     */
    public static String getSubStrExp(String billId, String exp) throws Exception
    {
        // C(S(v,L(v)-3,3),'4')---------C(S(v,3,4),'4')
        int three = PatternMatch.isThreeComma(exp);
        String subExp = exp.substring(2, three);// subExp--------S(v,L(v)-3,3)
        String temp = PatternMatch.processSubStrExp(billId, subExp);// temp-----S(v,5,3)
        CharSequence cs = temp.subSequence(0, temp.length());
        String str = exp.replace(subExp, cs);
        return str;
    }

    /**
     * 获取 $substr
     *
     * @param billId
     * @param exp
     * @return
     * @throws Exception
     */
    public static String processSubStrExp(String billId, String exp) throws Exception
    {
        // S(v,L(v)-6,1)
        String[] paras = PatternMatch.split(exp, ",");

        // Substr共三个参数，第1个为billId,只需处理后两个参数
        // 第2个参数
        if (paras[1].contains("L(v)"))
        {
            String str = Integer.toString(PatternMatch.invokeLength(billId));
            CharSequence cs = str.subSequence(0, str.length());
            paras[1] = paras[1].replace("L(v)", cs);
            paras[1] = Arithmetic.calculate(paras[1]);// 计算算术表达式字符串值，比如"11-6"
        }

        // 第3个参数
        // 需要截掉最后位的右括号才能进行算术运算
        paras[2] = paras[2].substring(0, paras[2].length() - 1);
        if (paras[2].contains("L(v)"))
        {
            String str = Integer.toString(PatternMatch.invokeLength(billId));
            CharSequence cs = str.subSequence(0, str.length());
            paras[2] = paras[2].replace("L(v)", cs);
            paras[2] = Arithmetic.calculate(paras[2]);// 计算算术表达式字符串值，比如"11-6"
        }

        StringBuffer sb = new StringBuffer();
        sb.append(paras[0]).append(",").append(paras[1]).append(",").append(paras[2]).append(")");
        return sb.toString();// S(v,5,1)
    }

    /**
     * 返回第三个逗号的 index
     *
     * @param exp
     *            表达式
     * @return
     */
    public static int isThreeComma(String exp)
    {
        int flag = 0;
        for (int i = 0; i < exp.length(); i++)
        {
            char c = exp.charAt(i);
            if (c == ',')
            {
                flag++;
                if (flag == 3)
                {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 处理 $substr
     *
     * @param billId
     *            电话号码
     * @param exp
     *            表达式
     * @return
     * @throws Exception
     */
    public static String processSubstr(String billId, String exp) throws Exception
    {
        // S(v,L(v)-6,1)
        String[] paras = PatternMatch.split(exp, ",");

        // Substr共三个参数，第1个为billId,只需处理后两个参数
        // 第2个参数
        if (paras[1].contains("L(v)"))
        {
            String str = Integer.toString(PatternMatch.invokeLength(billId)); // 获取长度
            CharSequence cs = str.subSequence(0, str.length());
            paras[1] = paras[1].replace("L(v)", cs);
            paras[1] = Arithmetic.calculate(paras[1]);// 计算算术表达式字符串值，比如"11-6"
        }

        // 第3个参数
        // 需要截掉最后位的右括号才能进行算术运算
        paras[2] = paras[2].substring(0, paras[2].length() - 1);
        if (paras[2].contains("L(v)"))
        {
            String str = Integer.toString(PatternMatch.invokeLength(billId));
            CharSequence cs = str.subSequence(0, str.length());
            paras[2] = paras[2].replace("L(v)", cs);
            paras[2] = Arithmetic.calculate(paras[2]);// 计算算术表达式字符串值，比如"11-6"
        }

        int begin = Integer.parseInt(paras[1]);
        int length = Integer.parseInt(paras[2]);
        String rst = PatternMatch.invokeSubstr(billId, begin, length);
        return rst;// S(v,5,1)
    }

    /**
     * 获取长度
     *
     * @param billId
     * @return
     * @throws Exception
     */
    public static int invokeLength(String billId) throws Exception
    {
        // L(v)
        return PatternMatch.length(billId);
    }
    /**
     * 从v第begin个字符开始截取长度为length的字符串
     *
     * @return
     * @throws Exception
     */
    public static String invokeSubstr(String billId, int begin, int length) throws Exception
    {
        // S(v,8,1)
        return PatternMatch.substr(billId, begin, length);
    }

    public static int length(String v)
    {
        return v.length();
    }

    /**
     * 从v第begin个字符开始截取长度为length的字符串
     *
     * @param v
     * @param begin
     * @param length
     * @return
     * @throws Exception
     */
    public static String substr(String v, int begin, int length) throws Exception
    {
        // if (begin == 0)
        // begin = 1;// 需要特殊处理
        // int beginIndex = begin - 1;
        int beginIndex = begin;
        int endIndex = begin + length;
        if (endIndex > v.length())
        {
            // throw new Exception("警告：传入参数有误【数组越界】,函数$substr(@bill_id,para1,para2)中para2<=" + (v.length() - begin + 1));
            throw  new Exception("the length is inconsistent");
        }
        // 从字符串索引(下标从0开始)为beginIndex的字符开始截取长度为endIndex-beginIndex 的字符串。
        String str = "ERROR";
        if (begin >= 0)
        {// 2011-11-10 开始小于0时，扔出错误字符串
            str = v.substring(beginIndex, endIndex);
        }
        return str;
    }
}
