package com.pl.airboss.crm.res.bean;

import java.util.Date;

public class ResPatternSegmentBean {
    private Long patternSegId;

    private String patternSegName;

    private String netId;

    private String segExp;

    private String segRpnExp;

    private Long priorty;

    private Long resType;

    private String state;

    private String regionId;

    private Long doneCode;

    private Date doneDate;

    private Date effectiveDate;

    private Date expireDate;

    private Date createDate;

    private Long opId;

    private Long orgId;

    private String ipriceMode;

    private String bitRel;

    private Long bitOrder;

    private String notes;

    public Long getPatternSegId() {
        return patternSegId;
    }

    public void setPatternSegId(Long patternSegId) {
        this.patternSegId = patternSegId;
    }

    public String getPatternSegName() {
        return patternSegName;
    }

    public void setPatternSegName(String patternSegName) {
        this.patternSegName = patternSegName == null ? null : patternSegName.trim();
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId == null ? null : netId.trim();
    }

    public String getSegExp() {
        return segExp;
    }

    public void setSegExp(String segExp) {
        this.segExp = segExp == null ? null : segExp.trim();
    }

    public String getSegRpnExp() {
        return segRpnExp;
    }

    public void setSegRpnExp(String segRpnExp) {
        this.segRpnExp = segRpnExp == null ? null : segRpnExp.trim();
    }

    public Long getPriorty() {
        return priorty;
    }

    public void setPriorty(Long priorty) {
        this.priorty = priorty;
    }

    public Long getResType() {
        return resType;
    }

    public void setResType(Long resType) {
        this.resType = resType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public Long getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(Long doneCode) {
        this.doneCode = doneCode;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getIpriceMode() {
        return ipriceMode;
    }

    public void setIpriceMode(String ipriceMode) {
        this.ipriceMode = ipriceMode == null ? null : ipriceMode.trim();
    }

    public String getBitRel() {
        return bitRel;
    }

    public void setBitRel(String bitRel) {
        this.bitRel = bitRel == null ? null : bitRel.trim();
    }

    public Long getBitOrder() {
        return bitOrder;
    }

    public void setBitOrder(Long bitOrder) {
        this.bitOrder = bitOrder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }
}