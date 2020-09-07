package com.pl.airboss.web.controller.monitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.bean.CfgServerListBean;
import com.pl.airboss.framework.dao.CfgServerListBeanMapper;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.service.Server;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * 服务器监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    @Autowired
    CfgServerListBeanMapper serverListBeanMapper;

    private String prefix = "monitor/server";

    @RequiresPermissions("app:server:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/serverList";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CfgServerListBean bean)throws Exception{
        startPage();
        List<CfgServerListBean> ret= serverListBeanMapper.selectAll();
        return getDataTable(ret);
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "主机监控", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:server:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgServerListBean bean)
    {
        CfgServerListBean b =serverListBeanMapper.selectByPrimaryKey(bean.getAddress());
        if (null != b)
        {
            return error("主机监控配置" + b.getAddress() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setStatus(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(serverListBeanMapper.insert(bean));
    }

    @PostMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(String address){

        CfgServerListBean r = serverListBeanMapper.selectByPrimaryKey(address);
        if(null != r ){
            return false;
        }else{
            return true;
        }
    }
    /**
     * 修改
     */
    @GetMapping("/edit/{address}")
    public String edit(@PathVariable("address") String userId, ModelMap mmap)
    {
        CfgServerListBean bean = serverListBeanMapper.selectByPrimaryKey(userId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 删除
     */
    @Log(title = "主机监控", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:server:delete")
    @PostMapping("/remove/{address}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("address") String address)
    {

        return toAjax(serverListBeanMapper.deleteByPrimaryKey(address));
    }


    /**
     * 保存
     */
    @Log(title = "服务监控", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:server:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgServerListBean bean)
    {
        return toAjax(serverListBeanMapper.updateByPrimaryKeySelective(bean));
    }

    @RequiresPermissions("monitor:server-detail:view")
    @GetMapping("/detail/{id}")
    public String server(@PathVariable("id") String address,ModelMap mmap) throws Exception
    {
        String url = "http://"+address+"/monitor/server/detail";
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection)u.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        InputStream inputStream = (InputStream) con.getContent();
        byte[]bs= new byte[inputStream.available()];
        inputStream.read(bs);
        String s = new String(bs);
        JSONObject json = (JSONObject)JSON.parse(s);
        Server server = new Server(json);
        //server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }

    @PostMapping("/detail")
    @ResponseBody
    public Server getserver()throws Exception{
        Server server = new Server();
        server.copyTo();
        return server;
    }
}
