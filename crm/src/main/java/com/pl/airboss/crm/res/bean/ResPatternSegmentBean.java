package com.pl.airboss.crm.res.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pl.airboss.framework.annotation.Excel;

import java.util.Date;

public class ResPatternSegmentBean {

    @Excel(name = "编号",cellType = Excel.ColumnType.STRING , type = Excel.Type.EXPORT)
    private Long patternSegId;

    @Excel(name = "号段名称",cellType = Excel.ColumnType.STRING)
    private String patternSegName;

    @Excel(name = "归属网元",cellType = Excel.ColumnType.STRING)
    private String netId;

    @Excel(name = "号段表达式",cellType = Excel.ColumnType.STRING)
    private String segExp;

    private String segRpnExp;

    private Long priorty;

    @Excel(name = "类型",cellType = Excel.ColumnType.STRING, type = Excel.Type.EXPORT)
    private Long resType;

    @Excel(name = "状态",cellType = Excel.ColumnType.STRING, type = Excel.Type.EXPORT)
    private String state;

    private String regionId;

    private Long doneCode;

    private Date doneDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生效时间",cellType = Excel.ColumnType.STRING)
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "失效时间",cellType = Excel.ColumnType.STRING)
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