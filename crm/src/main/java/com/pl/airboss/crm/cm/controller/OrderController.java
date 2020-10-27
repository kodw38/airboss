package com.pl.airboss.crm.cm.controller;

import com.pl.airboss.crm.cm.bean.AccountBean;
import com.pl.airboss.crm.cm.bean.CustGroupBean;
import com.pl.airboss.crm.cm.bean.CustPersonBean;
import com.pl.airboss.crm.cm.bean.UserBean;
import com.pl.airboss.crm.cm.service.interfaces.IOrderSV;
import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.FastDFSClient;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crm/order")
public class OrderController extends BaseController {
    static transient org.apache.commons.logging.Log log = LogFactory.getLog(OrderController.class);
    private String prefix = "crm/order";

    @Autowired
    IOrderSV orderSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("order:subscribe:view")
    @GetMapping("/subscribe")
    public String offer()
    {
        return prefix + "/subscribe";
    }

    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/queryCustomer/{psptTypeCode}/{psptId}")
    @ResponseBody
    public CustPersonBean queryCustomer(@PathVariable("psptTypeCode") String psptTypeCode, @PathVariable("psptId") String psptId){
        return orderSV.queryIndividualCustomer(psptTypeCode,psptId);
    }

    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/querySubscribe/{customerID}")
    @ResponseBody
    public TableDataInfo querySubscribe(@PathVariable("customerID") Long customerID){
        startPage();
        List<UserBean> ls = orderSV.queryUserByCustId(customerID);
        //return new TableDataInfo(ls,ls.size());
        return getDataTable(ls);
    }

    /**
     * 保存头像
     */
    @Log(title = "上传客户附件", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file)
    {
        SecOperatorBean currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty()) {
                //上传头像
                //String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                //upload to fastdfs server
                String path = FastDFSClient.saveFile(file, "");

                Map map = new HashMap();
                map.put("path",path);
                return AjaxResult.success(map);
            }
            return error();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    @GetMapping("/download/{path}")
    public void downloadAvatar(@PathVariable("path") String path, HttpServletResponse response, HttpServletRequest request){
        try {
            //String url = FastDFSClient.getTrackerUrl() + path;
            OutputStream out = response.getOutputStream();
            //response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            String group = path.substring(0,path.indexOf("/"));
            String name  = path.substring(path.indexOf("/")+1);
            byte[] byts=FastDFSClient.downFile(group,name);
            if(null !=byts) {
                out.write(byts);
            }
            out.close();

        }catch (Exception e){
            log.error("",e);
        }
    }


    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/queryCustAccount/{customerId}")
    @ResponseBody
    public List<AccountBean> queryCustAccount(@PathVariable("customerId") Long customerId){
        List<AccountBean> ls = orderSV.queryCustAccount(customerId);
        return ls;
    }

    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/queryGroup")
    @ResponseBody
    public TableDataInfo queryGroup(CustGroupBean cond){
        startPage();
        List<CustGroupBean> ls = orderSV.queryCustGroup(cond);
       // return new TableDataInfo(ls,ls.size());
        return getDataTable(ls);
    }

    @RequiresPermissions("order:subscribe:add")
    @PostMapping("/submitSubscribe/{phoneNumber}/{productId}/{accountId}/{groupCustId}/{groupRoleId}/{prepayTag}")
    @ResponseBody
    public AjaxResult submitSubscribe(CustPersonBean cust,String[] filePaths
            ,@PathVariable("phoneNumber")String phoneNumber
            ,@PathVariable("productId")Integer productId
            ,@PathVariable("accountId")Long accountId
            ,@PathVariable("groupCustId")Long groupCustId
            ,@PathVariable("groupRoleId")Long groupRoleId
            ,@PathVariable("prepayTag")Integer prepayTag
    ){
        try {
            Long userId = orderSV.submitSubscribe(cust, phoneNumber, productId, accountId, groupCustId, groupRoleId, prepayTag, filePaths);
            return AjaxResult.success(userId);
        }catch (Exception e){
            return error(e.getMessage());
        }
    }

    @RequiresPermissions("customer:groupCustomer:view")
    @GetMapping("/groupCustomer")
    public String groupCustomer(){
        return prefix+"/groupCustomer";
    }

    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/queryGroupCustomer/{psptTypeCode}/{psptId}")
    @ResponseBody
    public CustGroupBean queryGroupCustomer(@PathVariable("psptTypeCode") String psptTypeCode, @PathVariable("psptId") String psptId){
        return orderSV.queryGroupCustomer(psptTypeCode,psptId);
    }


    @RequiresPermissions("order:subscribe:view")
    @PostMapping("/queryGroupCustomer")
    @ResponseBody
    public TableDataInfo queryGroupCustomer(CustGroupBean bean){
        startPage();
        List<CustGroupBean> ls = orderSV.queryGroupCustomer(bean);
        return getDataTable(ls);
    }


    /*@RequiresPermissions("customer:groupCustomer:view")
    @PostMapping("/queryGroupSubscribe/{customerID}")
    @ResponseBody
    public TableDataInfo queryGroupCustomer(@PathVariable("customerID") Long custGroupId){
        startPage();
        List<UserBean> ls = orderSV.queryGroupUserByGroupCustId(custGroupId);
       // return new TableDataInfo(ls,ls.size());
        return getDataTable(ls);
    }*/

    @RequiresPermissions("customer:groupCustomer:view")
    @PostMapping("/queryGroupSubscribe")
    @ResponseBody
    public TableDataInfo queryGroupCustomer(UserBean bean){
        startPage();
        if (bean == null || bean.getCustId()==null || bean.getCustId()==0) {
            return getDataTable(new ArrayList<>());
        }
        List<UserBean> ls = orderSV.queryGroupUserByGroupCustId(bean.getCustId());
        return getDataTable(ls);
    }

    @RequiresPermissions("customer:groupCustomer:addUser")
    @PostMapping("/queryUser")
    @ResponseBody
    public TableDataInfo queryUser(UserBean cond){
        startPage();
        List<UserBean> ls = orderSV.queryUser(cond);
        return new TableDataInfo(ls,ls.size());
    }


    @RequiresPermissions("customer:groupCustomer:addUser")
    @PostMapping("/addUser2Group/{userId}/{groupId}/{accountId}/{roleType}")
    @ResponseBody
    public AjaxResult addUser2Group(@PathVariable("userId") Long userId,@PathVariable("groupId") Long groupId,@PathVariable("accountId") Long accountId,@PathVariable("roleType") String roleType){
        try {
            int n = orderSV.addUser2Group(userId, groupId, accountId,roleType);
            if (n > 0)
                return success();
            else
                return error();
        }catch (Exception e){
            return error(e.getMessage());
        }
    }

    @RequiresPermissions("customer:groupCustomer:view")
    @PostMapping("/queryGroupAccount/{custId}")
    @ResponseBody
    public TableDataInfo queryGroupAccount(@PathVariable("custId") Long custId){
        startPage();
        List<AccountBean> ls = orderSV.queryCustAccount(custId);
        return new TableDataInfo(ls,ls.size());
    }


    @RequiresPermissions("account:groupCustomer:add")
    @PostMapping("/newAccount")
    @ResponseBody
    public AjaxResult newAccount(AccountBean bean){
        int n = orderSV.addAccount(bean);
        if(n>0){
            return success();
        }else{
            return error();
        }
    }

    @RequiresPermissions("customer:group:add")
    @PostMapping("/newGroupCustomer")
    @ResponseBody
    public AjaxResult newGroupCustomer(CustGroupBean bean){
        int n = orderSV.addCustGroup(bean);
        if(n>0){
            return success();
        }else{
            return error();
        }
    }


    @RequiresPermissions("customer:group:delete")
    @PostMapping("/removeGroupCustomer/{custId}")
    @ResponseBody
    public AjaxResult removeGroupCustomer(@PathVariable("custId")Long custId){
        try {
            int n = orderSV.removeGroupCust(custId);
            if (n > 0) {
                return success();
            } else {
                return error();
            }
        }catch (Exception e){
            return error(e.getMessage());
        }
    }
}
