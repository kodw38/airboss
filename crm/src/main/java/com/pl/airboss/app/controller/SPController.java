package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/26.
 */

import com.pl.airboss.app.bean.SPBillAcctBean;
import com.pl.airboss.app.dao.SPBillAcctBeanMapper;
import com.pl.airboss.utils.DateUtils;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName SPController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/26 14:08
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/sp")
public class SPController extends BaseController{

    @Autowired
    SPBillAcctBeanMapper billAcctBeanMapper;

    static String prefix="app/sp/";

    @GetMapping("/acct")
    @RequiresPermissions("app:sp:view")
    public String spAcctUrl(ModelMap map){
        List<String> ls = DateUtils.getBeforeYearMonths(6);
        map.put("billCycle",ls);
        return prefix+"acct";
    }

    @PostMapping("/acctList")
    @RequiresPermissions("app:sp:view")
    @ResponseBody
    public TableDataInfo list(SPBillAcctBean bean){
        startPage();
        List<SPBillAcctBean> ls = billAcctBeanMapper.list(bean);
        return getDataTable(ls);
    }
}
