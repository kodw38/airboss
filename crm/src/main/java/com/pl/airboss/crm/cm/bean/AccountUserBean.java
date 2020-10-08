package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class AccountUserBean extends AccountUserBeanKey {
    private Long payrelationId;

    private Short payPriority;

    private String limitType;

    private Long limitValue;

    private Integer endDate;

    private Integer acctChno;

    private Date updateTime;

    private String updateDepartId;

    private String updateStaffId;

    private String fillTag;

    public Long getPayrelationId() {
        return payrelationId;
    }

    public void setPayrelationId(Long payrelationId) {
        this.payrelationId = payrelationId;
    }

    public Short getPayPriority() {
        return payPriority;
    }

    public void setPayPriority(Short payPriority) {
        this.payPriority = payPriority;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType == null ? null : limitType.trim();
    }

    public Long getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Long limitValue) {
        this.limitValue = limitValue;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public Integer getAcctChno() {
        return acctChno;
    }

    public void setAcctChno(Integer acctChno) {
        this.acctChno = acctChno;
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

    public String getFillTag() {
        return fillTag;
    }

    public void setFillTag(String fillTag) {
        this.fillTag = fillTag == null ? null : fillTag.trim();
    }
}