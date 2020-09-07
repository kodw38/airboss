package com.pl.airboss.framework.mybatisplugin;/**
 * Created by admin on 2020/7/13.
 */

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.codehaus.plexus.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName SplitTablePlugin
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/7/13 16:36
 * @Version 1.0
 **/
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = { Connection.class, Integer.class }
        )
})
public class SplitTablePlugin implements Interceptor {

    private static final ReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                defaultReflectorFactory
        );
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        id = id.substring(0, id.lastIndexOf('.'));
        Class clazz = Class.forName(id);
        // 获取TableSplit注解
        TableSplit tableSplit = (TableSplit)clazz.getAnnotation(TableSplit.class);
        if ( tableSplit != null && metaObject.getOriginalObject() instanceof RoutingStatementHandler) {
            Class<? extends ITableSplitStrategy> strategyClazz = tableSplit.splitStrategy();
            ITableSplitStrategy strategy = strategyClazz.newInstance();
            RoutingStatementHandler sh = (RoutingStatementHandler)metaObject.getOriginalObject();
            if(null != sh.getBoundSql()) {
                Object bean=sh.getBoundSql().getParameterObject();
                String sql = sh.getBoundSql().getSql();
                if(null != bean && null != sql) {
                    List<String> tableNames = getTagsIncludeMark(sql,"[","]");
                    if(null != tableNames && tableNames.size()==1) {
                        String tableName = tableNames.get(0);
                        List<String> subfix = getTagsIncludeMark(tableName,"(",")");
                        if(null != subfix && subfix.size()==1) {
                            String sub = subfix.get(0);
                            String f = sub.substring(1,sub.length()-1);
                            Object v=null;
                            if(bean instanceof Map){
                                v = ((Map)bean).get(f);
                            }else {
                                Field tf=null;
                                try {
                                    tf = bean.getClass().getDeclaredField(f.trim());
                                }catch (Exception ex){
                                    tf = bean.getClass().getSuperclass().getDeclaredField(f.trim());
                                }
                                tf.setAccessible(true);
                                v = tf.get(bean);
                            }

                            String rsub = strategy.tableSplit(v);
                            String newTableName = tableName.replace(sub,rsub);
                            newTableName = newTableName.substring(1,newTableName.length()-1);
                            // 用新sql代替旧sql, 完成所谓的sql rewrite
                            metaObject.setValue("delegate.boundSql.sql", StringUtils.replace(sql,tableName, newTableName));
                        }
                    }
                }
            }
        }
        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身, 减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    static List getTagsIncludeMark(String s,String beginMark,String endMark){
        StringBuffer sb = new StringBuffer();
        List ret = new ArrayList();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='\t') continue;
            if(s.charAt(i)=='\n') continue;
            if(s.charAt(i)=='\r') continue;
            if(s.charAt(i)==' ') continue;
            sb.append(s.charAt(i));
        }
        String xml = sb.toString();
        while(true){
            int beginStart = xml.indexOf(beginMark);
            if (beginStart < 0) return ret;
            int beginEnd = beginStart+beginMark.length();
            int endStart = xml.indexOf(endMark, beginEnd);
            int endend  = endStart+endMark.length();
            if (endStart < 0) return ret;
            if(beginStart>=0){
                ret.add(xml.substring(beginStart,endend));
                xml = xml.substring(0,beginStart)+ xml.substring(endend);
            }else{
                xml = xml.substring(endStart);
            }
        }
    }
}
