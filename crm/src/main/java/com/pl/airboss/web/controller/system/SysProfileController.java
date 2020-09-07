package com.pl.airboss.web.controller.system;

import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.Constants;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.service.ISysService;
import com.pl.airboss.web.service.SysPasswordService;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.FastDFSClient;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 个人信息 业务处理
 * 
 */
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    @Qualifier("SysService")
    private ISysService sysService;
    
    @Autowired
    private SysPasswordService passwordService;
    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        mmap.put("user", user);
        mmap.put("roleGroup", sysService.getRolesByOpId(user.getRecId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        if (passwordService.matches(user, password))
        {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        mmap.put("user", sysService.getOperatorById(user.getRecId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword))
        {
            //user.setSalt(ShiroUtils.randomSalt());
            user.setLoginPwd(passwordService.encryptPassword(user.getLoginCode(), newPassword, Constants.SALT));
            if (sysService.resetUserPwd(user))
            {
                ShiroUtils.setSysUser(sysService.getOperatorById(user.getRecId()));
                return success();
            }
            return error();
        }
        else
        {
            return error("修改密码失败，旧密码错误");
        }
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        mmap.put("user", sysService.getOperatorById(user.getRecId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap)
    {
        SecOperatorBean user = ShiroUtils.getSysUser();
        mmap.put("user", sysService.getOperatorById(user.getRecId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SecOperatorBean user)
    {
        SecOperatorBean currentUser = ShiroUtils.getSysUser();
        currentUser.setLoginCode(user.getLoginCode());
        currentUser.setEmail(user.getEmail());
        currentUser.setOpName(user.getOpName());
        currentUser.setPhoneNum(user.getPhoneNum());
        if (sysService.updateUserInfo(currentUser))
        {
            ShiroUtils.setSysUser(sysService.getOperatorById(currentUser.getRecId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file)
    {
        SecOperatorBean currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty())
            {
                //上传头像
                //String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                //upload to fastdfs server
                String avatar = FastDFSClient.saveFile(file,"/system/user/profile/downloadAvatar?path=");

                currentUser.setAvatar(avatar);
                if (sysService.updateUserInfo(currentUser))
                {
                    ShiroUtils.setSysUser(sysService.getOperatorById(currentUser.getRecId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }

    @GetMapping("/downloadAvatar")
    public void downloadAvatar(@RequestParam("path") String path,HttpServletResponse response,HttpServletRequest request){
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
            }else{
                File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"static/img/profile.jpg");
                InputStream in =new FileInputStream(f);
                byte[] b= new byte[in.available()];
                in.read(b);
                out.write(b);
                in.close();
            }
            out.close();

        }catch (Exception e){
            log.error("",e);
        }
    }


}
