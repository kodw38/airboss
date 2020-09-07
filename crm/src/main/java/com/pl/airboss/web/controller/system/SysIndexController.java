package com.pl.airboss.web.controller.system;

import com.pl.airboss.framework.config.Global;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.service.ISysConfigService;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.bean.SecFunctionBean;
import com.pl.airboss.web.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author pl
 */
@Controller
public class SysIndexController
{
    @Autowired
    private ISysService sysService;

    @Autowired
    private ISysConfigService configService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SecOperatorBean user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SecFunctionBean> menus = sysService.getMenuesByLoginCode(user.getLoginCode());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
