package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class AccountBean extends AccountBeanKey {
    private String netTypeCode;

    private String eparchyCode;

    private String cityCode;

    private Long custId;

    private String payName;

    private String payModeCode;

    private Long scoreValue;

    private Short creditClassId;

    private Long basicCreditValue;

    private Long creditValue;

    private Integer creditControlId;

    private Long debutyUserId;

    private String debutyCode;

    private String contractNo;

    private String removeTag;

    private Date openDate;

    private Date removeDate;

    private Date updateTime;

    private String updateDepartId;

    private String updateStaffId;

    public String getNetTypeCode() {
        return netTypeCode;
    }

    public void setNetTypeCode(String netTypeCode) {
        this.netTypeCode = netTypeCode == null ? null : netTypeCode.trim();
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

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public String getPayModeCode() {
        return payModeCode;
    }

    public void setPayModeCode(String payModeCode) {
        this.payModeCode = payModeCode == null ? null : payModeCode.trim();
    }

    public Long getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Long scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Short getCreditClassId() {
        return creditClassId;
    }

    public void setCreditClassId(Short creditClassId) {
        this.creditClassId = creditClassId;
    }

    public Long getBasicCreditValue() {
        return basicCreditValue;
    }

    public void setBasicCreditValue(Long basicCreditValue) {
        this.basicCreditValue = basicCreditValue;
    }

    public Long getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Long creditValue) {
        this.creditValue = creditValue;
    }

    public Integer getCreditControlId() {
        return creditControlId;
    }

    public void setCreditControlId(Integer creditControlId) {
        this.creditControlId = creditControlId;
    }

    public Long getDebutyUserId() {
        return debutyUserId;
    }

    public void setDebutyUserId(Long debutyUserId) {
        this.debutyUserId = debutyUserId;
    }

    public String getDebutyCode() {
        return debutyCode;
    }

    public void setDebutyCode(String debutyCode) {
        this.debutyCode = debutyCode == null ? null : debutyCode.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getRemoveTag() {
        return removeTag;
    }

    public void setRemoveTag(String removeTag) {
        this.removeTag = removeTag == null ? null : removeTag.trim();
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
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