package com.pl.airboss.crm.res.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Arithmetic {
    public static final transient Log log = LogFactory.getLog(Arithmetic.class);

    private final static String OP1 = "+";

    /**
     * -
     */
    private final static String OP2 = "-";

    /**
     * *
     */
    private final static String OP3 = "*";

    /**
     * /
     */
    private final static String OP4 = "/";

    /**
     * (
     */
    private final static String OPSTART = "(";

    /**
     * )
     */
    private final static String OPEND = ")";

    public Arithmetic(String exp)
    {
        log.error(exp);// modify by liuqs， date：2015-04-09
    }

    /**
     * 分析四则运算表达式，将数字与运算符进行分解
     */
    public static List parse(String exp)
    {
        // 四则运算解析
        List expList = new ArrayList();
        int length = exp.length();

        StringBuffer tempStr = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            String tempChar = exp.substring(i, i + 1);
            if (isNumber(tempChar))
            {
                tempStr.append(tempChar);
            } else
            {
                if (!StringUtils.isEmpty(tempStr.toString()))
                {
                    expList.add(tempStr.toString());
                }
                expList.add(tempChar);
            }
        }
        if (!StringUtils.isEmpty(tempStr.toString()))
        {
            expList.add(tempStr);
        }

        return expList;

    }

    /**
     * 判断当前字符或字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str)
    {
        return str.startsWith("0") || str.startsWith("1") || str.startsWith("2") || str.startsWith("3") || str.startsWith("4") || str.startsWith("5") || str.startsWith("6") || str.startsWith("7") || str.startsWith("8") || str.startsWith("9") || str.startsWith(".");

    }

    /**
     * 判断当前字符是否是 (
     *
     * @param str
     * @return
     */
    public static boolean isParenthesesStart(String str)
    {
        return str.equals(OPSTART);
    }

    /**
     * 判断当前字符是否是 )
     *
     * @param str
     * @return
     */
    public static boolean isParenthesesEnd(String str)
    {
        return str.equals(OPEND);
    }

    /**
     * 判断当前字符是否是高优先级运算符 * /
     *
     * @param str
     * @return
     */
    public static boolean isHeighOperator(String str)
    {
        return str.equals(OP3) || str.equals(OP4);
    }

    /**
     * 对比两个字符串的优先级
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compare(String str1, String str2)
    {
        if (str1.equals(OPSTART))
        {
            return false;
        }
        if (isHeighOperator(str2))
        {
            return false;
        } else
        {
            return isHeighOperator(str1);
        }
    }

    /**
     * 将分解后的四则运算列表构建成逆波兰表达式列表
     */
    public static List createRPN(String exp)
    {
        List expList = Arithmetic.parse(exp);

        // 存放逆波兰表达式
        List rpnList = new ArrayList();
        Stack stack = new Stack();

        int length = expList.size();
        for (int i = 0; i < length; i++)
        {
            String c = (String) expList.get(i);

            // 如果是数字，直接放到逆波兰链表的最后
            if (isNumber(c))
            {
                rpnList.add(c);
            } else
            {
                // 如果不是数字

                // 如果是左括号，则直接将左括号压入栈
                if (isParenthesesStart(c))
                {
                    stack.push(c);
                } else if (isParenthesesEnd(c))
                {
                    // 如果是右括号

                    // 进行出栈操作，直到栈为空或者遇到第一个左括号
                    while (!stack.isEmpty())
                    {
                        // 将栈顶字符串做出栈操作
                        String tempC = stack.pop();
                        if (!tempC.equals(OPSTART))
                        {
                            // 如果不是左括号，则将字符串直接放到逆波兰链表的最后
                            rpnList.add(tempC);
                        } else
                        {
                            // 如果是左括号，退出循环操作
                            break;
                        }
                    }
                } else
                {
                    // 如果栈内为空
                    if (stack.isEmpty())
                    {
                        // 将当前字符串直接压栈
                        stack.push(c);
                    } else
                    {
                        // 如果栈不为空

                        // 比较栈顶字符串与当前字符串的优先级，
                        if (compare(stack.top(), c))
                        {
                            // 如果栈顶元素的优先级大于当前字符串,则进行出栈操作
                            // 将栈顶元素直接放到逆波兰链表的最后
                            // 直到栈内为空，或者当前元素的优先级不小于栈顶元素优先级
                            while (!stack.isEmpty() && compare(stack.top(), c))
                            {
                                rpnList.add(stack.pop());
                            }
                        }
                        // 将当前字符串直接压栈
                        stack.push(c);
                    }
                }

            }
        }

        // 如果栈不为空，则将栈中所有元素出栈放到逆波兰链表的最后
        while (!stack.isEmpty())
        {
            rpnList.add(stack.pop());
        }

        return rpnList;
    }

    /**
     * 通过逆波兰表达式计算结果
     *
     * @return
     */
    public static String calculate(String exp)
    {
        Stack stack = new Stack();
        List rpnList = Arithmetic.createRPN(exp);
        int length = rpnList.size();
        for (int i = 0; i < length; i++)
        {
            String temp = (String) rpnList.get(i);
            if (isNumber(temp))
            {
                stack.push(temp);
            } else
            {
                int number1 = Integer.parseInt(stack.pop());
                int number2 = Integer.parseInt(stack.pop());

                int rst = 0;

                if (temp.equals(OP1))
                {
                    rst = number1 + number2;
                } else if (temp.equals(OP2))
                {
                    rst = number2 - number1;
                } else if (temp.equals(OP3))
                {
                    rst = number2 * number1;
                } else if (temp.equals(OP4))
                {
                    rst = number2 / number1;
                }
                stack.push(Integer.toString(rst));
            }
        }

        return stack.pop();
    }

    /**
     * 获取逆波兰表达式字符串
     *
     * @return
     */
    public static String getRPN(List rpnList)
    {
        StringBuffer rpn = new StringBuffer();
        int rpnLength = rpnList.size();
        for (int i = 0; i < rpnLength; i++)
        {
            rpn.append(rpnList.get(i)).append(" ");
        }
        return rpn.toString();
    }
}
