package com.pl.airboss.web.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pl.airboss.framework.annotation.Excel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SecRoleBean {
    private Integer recId;
    @Excel(name = "角色编号", cellType = Excel.ColumnType.STRING)
    private String roleCode;
    @Excel(name = "角色类型", cellType = Excel.ColumnType.NUMERIC,readConverterExp="1=管理员,2=业务角色")
    private String roleType;
    @Excel(name = "角色名称", cellType = Excel.ColumnType.STRING)
    private String roleName;
    @Excel(name = "继承角色", cellType = Excel.ColumnType.STRING)
    private String extendRoles;
    @Excel(name = "创建日期",dateFormat="yyyy-MM-dd HH:mm:ss", cellType = Excel.ColumnType.STRING)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @Excel(name = "授权码", cellType = Excel.ColumnType.STRING)
    private String perms;

    public List getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List menuIds) {
        this.menuIds = menuIds;
    }

    List menuIds;

    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    private String ext1;

    private Integer opId;

    private Integer orgId;

    private String doneCode;

    private Date doneDate;

    private String remark;

    private Map params;

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    @Excel(name = "状态(1:有效,0:无效)", cellType = Excel.ColumnType.STRING)
    private Integer status;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getExtendRoles() {
        return extendRoles;
    }

    public void setExtendRoles(String extendRoles) {
        this.extendRoles = extendRoles == null ? null : extendRoles.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
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


}