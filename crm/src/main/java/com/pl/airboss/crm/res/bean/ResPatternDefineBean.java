package com.pl.airboss.crm.res.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pl.airboss.framework.annotation.Excel;

import java.util.Date;

public class ResPatternDefineBean {
    @Excel(name = "模式编号",cellType = Excel.ColumnType.STRING , type = Excel.Type.EXPORT)
    private Long patternDefId;

    @Excel(name = "模式名称",cellType = Excel.ColumnType.STRING)
    private String patternDefName;

    @Excel(name = "号码模式",cellType = Excel.ColumnType.STRING)
    private String bitRel;

    private Long bitOrder;

    private String bitRestrictExp;

    private String bitRestrictRpn;

    private Long priorty;

    @Excel(name = "类型",cellType = Excel.ColumnType.STRING, type = Excel.Type.EXPORT)
    private Long resType;

    @Excel(name = "状态",cellType = Excel.ColumnType.STRING, type = Excel.Type.EXPORT)
    private String state;

    private String regionId;

    private Long doneCode;

    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生效时间",cellType = Excel.ColumnType.STRING)
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "失效时间",cellType = Excel.ColumnType.STRING)
    private Date expireDate;

    private Date doneDate;

    private Long opId;

    private Long orgId;

    private Long ipriceMode;

    private String notes;

    public Long getPatternDefId() {
        return patternDefId;
    }

    public void setPatternDefId(Long patternDefId) {
        this.patternDefId = patternDefId;
    }

    public String getPatternDefName() {
        return patternDefName;
    }

    public void setPatternDefName(String patternDefName) {
        this.patternDefName = patternDefName == null ? null : patternDefName.trim();
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

    public String getBitRestrictExp() {
        return bitRestrictExp;
    }

    public void setBitRestrictExp(String bitRestrictExp) {
        this.bitRestrictExp = bitRestrictExp == null ? null : bitRestrictExp.trim();
    }

    public String getBitRestrictRpn() {
        return bitRestrictRpn;
    }

    public void setBitRestrictRpn(String bitRestrictRpn) {
        this.bitRestrictRpn = bitRestrictRpn == null ? null : bitRestrictRpn.trim();
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

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
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

    public Long getIpriceMode() {
        return ipriceMode;
    }

    public void setIpriceMode(long ipriceMode) {
        this.ipriceMode = ipriceMode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }
}