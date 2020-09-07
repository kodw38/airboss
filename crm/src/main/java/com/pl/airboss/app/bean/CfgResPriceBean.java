package com.pl.airboss.app.bean;

import java.util.Date;

public class CfgResPriceBean {
    private Integer recId;

    private String srcSp;

    private Integer srcRate;

    private String targetSp;

    private Integer targetRate;

    private String cycleType;

    private Integer calType;

    private String resourceType;

    private String resourceUnit;

    private Integer resourceValue;

    private String currency;

    private Integer resourcePrice;

    private Date createDate;

    private Date effectiveDate;

    private Date expireDate;

    private Integer opId;

    private Integer orgId;

    private String doneCode;

    private Date doneDate;

    private String remark;

    private Integer state;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getSrcSp() {
        return srcSp;
    }

    public void setSrcSp(String srcSp) {
        this.srcSp = srcSp == null ? null : srcSp.trim();
    }

    public Integer getSrcRate() {
        return srcRate;
    }

    public void setSrcRate(Integer srcRate) {
        this.srcRate = srcRate;
    }

    public String getTargetSp() {
        return targetSp;
    }

    public void setTargetSp(String targetSp) {
        this.targetSp = targetSp == null ? null : targetSp.trim();
    }

    public Integer getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(Integer targetRate) {
        this.targetRate = targetRate;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getCalType() {
        return calType;
    }

    public void setCalType(Integer calType) {
        this.calType = calType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceUnit() {
        return resourceUnit;
    }

    public void setResourceUnit(String resourceUnit) {
        this.resourceUnit = resourceUnit == null ? null : resourceUnit.trim();
    }

    public Integer getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(Integer resourceValue) {
        this.resourceValue = resourceValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(Integer resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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