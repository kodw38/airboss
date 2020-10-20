package com.pl.airboss.crm.res.bean;

import java.util.Date;

public class ResSelpriceModeBean extends ResSelpriceModeBeanKey {
    private String modeDesc;

    private Long isUsed;

    private Long reserveFee;

    private Long points;

    private Long orderFee;

    private Long orderDays;

    private Long chooseLevel;

    private String countyId;

    private Long doneCode;

    private Date doneDate;

    private Date effectiveDate;

    private Date expireDate;

    private Date createDate;

    private Long opId;

    private Long orgId;

    private String notes;

    private Long presentMonth;

    private Long depositMonth;

    private Long depositAmount;

    private String patternName;

    private String ifeeType;

    private String state;

    public String getModeDesc() {
        return modeDesc;
    }

    public void setModeDesc(String modeDesc) {
        this.modeDesc = modeDesc == null ? null : modeDesc.trim();
    }

    public Long getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Long isUsed) {
        this.isUsed = isUsed;
    }

    public Long getReserveFee() {
        return reserveFee;
    }

    public void setReserveFee(Long reserveFee) {
        this.reserveFee = reserveFee;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Long getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Long orderFee) {
        this.orderFee = orderFee;
    }

    public Long getOrderDays() {
        return orderDays;
    }

    public void setOrderDays(Long orderDays) {
        this.orderDays = orderDays;
    }

    public Long getChooseLevel() {
        return chooseLevel;
    }

    public void setChooseLevel(Long chooseLevel) {
        this.chooseLevel = chooseLevel;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Long getPresentMonth() {
        return presentMonth;
    }

    public void setPresentMonth(Long presentMonth) {
        this.presentMonth = presentMonth;
    }

    public Long getDepositMonth() {
        return depositMonth;
    }

    public void setDepositMonth(Long depositMonth) {
        this.depositMonth = depositMonth;
    }

    public Long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName == null ? null : patternName.trim();
    }

    public String getIfeeType() {
        return ifeeType;
    }

    public void setIfeeType(String ifeeType) {
        this.ifeeType = ifeeType == null ? null : ifeeType.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}