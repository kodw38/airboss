package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class AcctFeepolicyParamBean extends AcctFeepolicyParamBeanKey {
    private Long acctId;

    private Long feepolicyId;

    private String feepolicyParamValue;

    private Date endDate;

    private Date updateTime;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getFeepolicyId() {
        return feepolicyId;
    }

    public void setFeepolicyId(Long feepolicyId) {
        this.feepolicyId = feepolicyId;
    }

    public String getFeepolicyParamValue() {
        return feepolicyParamValue;
    }

    public void setFeepolicyParamValue(String feepolicyParamValue) {
        this.feepolicyParamValue = feepolicyParamValue == null ? null : feepolicyParamValue.trim();
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
}