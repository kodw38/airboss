package com.pl.airboss.framework.mybatisplugin;/**
 * Created by admin on 2020/7/13.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateTableSplitStrategy
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/7/13 16:38
 * @Version 1.0
 **/
public class YYYYMMTableSplitStrategy implements ITableSplitStrategy {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    @Override
    public String tableSplit(Object o) {
        return "_" + sdf.format((Date)o);
    }
}
