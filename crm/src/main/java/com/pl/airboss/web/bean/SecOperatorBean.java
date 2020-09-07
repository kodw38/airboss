package com.pl.airboss.web.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pl.airboss.framework.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SecOperatorBean implements Serializable{
    private Integer recId;

    @Excel(name = "用户名称",cellType = Excel.ColumnType.STRING)
    private String opName;
    @Excel(name = "登录名称",cellType = Excel.ColumnType.STRING)
    private String loginCode;
    private String loginPwd;
    @Excel(name = "组织编号",cellType = Excel.ColumnType.STRING)
    private Integer organizeId;
    @Excel(name = "创建日期",cellType = Excel.ColumnType.STRING)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @Excel(name = "电话号码",cellType = Excel.ColumnType.STRING)
    private String phoneNum;

    private String avatar="";
    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    @Excel(name = "是否系统管理员",cellType = Excel.ColumnType.NUMERIC)
    private Integer isAdmin;
    @Excel(name = "邮件地址",cellType = Excel.ColumnType.STRING)
    private String email;

    private String ext1;

    private String ext2;
    @Excel(name = "性别(0:男,1:女)",cellType = Excel.ColumnType.NUMERIC)
    private int sex;


    private Integer opId;

    private Integer orgId;

    private String doneCode;

    private Date doneDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    private String remark;

    private String organizeName;

    private List<String> roleIds;

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
    @Excel(name = "状态(1:有效,0:无效)",cellType = Excel.ColumnType.NUMERIC)
    private Integer state;

    private Map params;

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Integer getRecId() {
        return recId;
    }


    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode == null ? null : loginCode.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public Integer getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(Integer organizeId) {
        this.organizeId = organizeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(String doneCode) {
        this.doneCode = doneCode == null ? null : doneCode.trim();
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}