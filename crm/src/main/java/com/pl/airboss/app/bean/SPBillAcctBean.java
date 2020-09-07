package com.pl.airboss.app.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SPBillAcctBean {
    private Integer recId;

    private String spCode;

    private Integer isSender;

    private Integer resourceType;

    private String cycleType;

    private String billCycle;

    private Integer sumNum;

    private Integer costFee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Integer adjustNum;

    private String opCode;

    private Integer status;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode == null ? null : spCode.trim();
    }

    public Integer getIsSender() {
        return isSender;
    }

    public void setIsSender(Integer isSender) {
        this.isSender = isSender;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public String getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(String billCycle) {
        this.billCycle = billCycle == null ? null : billCycle.trim();
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public Integer getCostFee() {
        return costFee;
    }

    public void setCostFee(Integer costFee) {
        this.costFee = costFee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAdjustNum() {
        return adjustNum;
    }

    public void setAdjustNum(Integer adjustNum) {
        this.adjustNum = adjustNum;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode == null ? null : opCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}