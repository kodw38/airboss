package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class CustGroupBean extends CustomerBean{
    private Integer partitionId;

    private Long custId;

    private String groupId;

    private String custName;

    private String groupType;

    private String classId;

    private String custClassType;

    private String groupAttr;

    private String groupStatus;

    private String groupAddr;

    private String provinceCode;

    private String eparchyCode;

    private String cityCode;

    private String superGroupId;

    private String pnationalGroupId;

    private String mpGroupCustCode;

    private String unifyPayCode;

    private String custManagerId;

    private Date assignDate;

    private String assignStaffId;

    private String website;

    private String faxNbr;

    private String email;

    private String postCode;

    private String groupMgrSn;

    private String groupMgrCustName;

    private String custServNbr;

    private Date updateTime;

    private String updateStaffId;

    private String updateDepartId;

    private String remark;

    private String custManagerIdB;

    private String rsrvTag1;

    private String rsrvStr2;

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getCustClassType() {
        return custClassType;
    }

    public void setCustClassType(String custClassType) {
        this.custClassType = custClassType == null ? null : custClassType.trim();
    }

    public String getGroupAttr() {
        return groupAttr;
    }

    public void setGroupAttr(String groupAttr) {
        this.groupAttr = groupAttr == null ? null : groupAttr.trim();
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus == null ? null : groupStatus.trim();
    }

    public String getGroupAddr() {
        return groupAddr;
    }

    public void setGroupAddr(String groupAddr) {
        this.groupAddr = groupAddr == null ? null : groupAddr.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getEparchyCode() {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode) {
        this.eparchyCode = eparchyCode == null ? null : eparchyCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getSuperGroupId() {
        return superGroupId;
    }

    public void setSuperGroupId(String superGroupId) {
        this.superGroupId = superGroupId == null ? null : superGroupId.trim();
    }

    public String getPnationalGroupId() {
        return pnationalGroupId;
    }

    public void setPnationalGroupId(String pnationalGroupId) {
        this.pnationalGroupId = pnationalGroupId == null ? null : pnationalGroupId.trim();
    }

    public String getMpGroupCustCode() {
        return mpGroupCustCode;
    }

    public void setMpGroupCustCode(String mpGroupCustCode) {
        this.mpGroupCustCode = mpGroupCustCode == null ? null : mpGroupCustCode.trim();
    }

    public String getUnifyPayCode() {
        return unifyPayCode;
    }

    public void setUnifyPayCode(String unifyPayCode) {
        this.unifyPayCode = unifyPayCode == null ? null : unifyPayCode.trim();
    }

    public String getCustManagerId() {
        return custManagerId;
    }

    public void setCustManagerId(String custManagerId) {
        this.custManagerId = custManagerId == null ? null : custManagerId.trim();
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public String getAssignStaffId() {
        return assignStaffId;
    }

    public void setAssignStaffId(String assignStaffId) {
        this.assignStaffId = assignStaffId == null ? null : assignStaffId.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getFaxNbr() {
        return faxNbr;
    }

    public void setFaxNbr(String faxNbr) {
        this.faxNbr = faxNbr == null ? null : faxNbr.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getGroupMgrSn() {
        return groupMgrSn;
    }

    public void setGroupMgrSn(String groupMgrSn) {
        this.groupMgrSn = groupMgrSn == null ? null : groupMgrSn.trim();
    }

    public String getGroupMgrCustName() {
        return groupMgrCustName;
    }

    public void setGroupMgrCustName(String groupMgrCustName) {
        this.groupMgrCustName = groupMgrCustName == null ? null : groupMgrCustName.trim();
    }

    public String getCustServNbr() {
        return custServNbr;
    }

    public void setCustServNbr(String custServNbr) {
        this.custServNbr = custServNbr == null ? null : custServNbr.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    public String getUpdateDepartId() {
        return updateDepartId;
    }

    public void setUpdateDepartId(String updateDepartId) {
        this.updateDepartId = updateDepartId == null ? null : updateDepartId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCustManagerIdB() {
        return custManagerIdB;
    }

    public void setCustManagerIdB(String custManagerIdB) {
        this.custManagerIdB = custManagerIdB == null ? null : custManagerIdB.trim();
    }

    public String getRsrvTag1() {
        return rsrvTag1;
    }

    public void setRsrvTag1(String rsrvTag1) {
        this.rsrvTag1 = rsrvTag1 == null ? null : rsrvTag1.trim();
    }

    public String getRsrvStr2() {
        return rsrvStr2;
    }

    public void setRsrvStr2(String rsrvStr2) {
        this.rsrvStr2 = rsrvStr2 == null ? null : rsrvStr2.trim();
    }
}