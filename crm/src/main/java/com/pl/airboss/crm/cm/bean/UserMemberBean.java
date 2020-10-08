package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class UserMemberBean extends UserMemberBeanKey {
    private Long vpnId;

    private String memberRoleType;

    private String memberRoleCode;

    private Long memberRoleId;

    private String memberRoleNumber;

    private Short discntPriority;

    private String pVpnTag;

    private Date startDate;

    private Date endDate;

    private Date updateTime;

    private String updateDepartId;

    private String updateStaffId;

    public Long getVpnId() {
        return vpnId;
    }

    public void setVpnId(Long vpnId) {
        this.vpnId = vpnId;
    }

    public String getMemberRoleType() {
        return memberRoleType;
    }

    public void setMemberRoleType(String memberRoleType) {
        this.memberRoleType = memberRoleType == null ? null : memberRoleType.trim();
    }

    public String getMemberRoleCode() {
        return memberRoleCode;
    }

    public void setMemberRoleCode(String memberRoleCode) {
        this.memberRoleCode = memberRoleCode == null ? null : memberRoleCode.trim();
    }

    public Long getMemberRoleId() {
        return memberRoleId;
    }

    public void setMemberRoleId(Long memberRoleId) {
        this.memberRoleId = memberRoleId;
    }

    public String getMemberRoleNumber() {
        return memberRoleNumber;
    }

    public void setMemberRoleNumber(String memberRoleNumber) {
        this.memberRoleNumber = memberRoleNumber == null ? null : memberRoleNumber.trim();
    }

    public Short getDiscntPriority() {
        return discntPriority;
    }

    public void setDiscntPriority(Short discntPriority) {
        this.discntPriority = discntPriority;
    }

    public String getpVpnTag() {
        return pVpnTag;
    }

    public void setpVpnTag(String pVpnTag) {
        this.pVpnTag = pVpnTag == null ? null : pVpnTag.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateDepartId() {
        return updateDepartId;
    }

    public void setUpdateDepartId(String updateDepartId) {
        this.updateDepartId = updateDepartId == null ? null : updateDepartId.trim();
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }
}