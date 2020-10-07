package com.pl.airboss.crm.ac.bean;

import java.util.Date;

public class AccessLogBean extends AccessLogBeanKey {
    private Long rowid;

    private Long chargeId;

    private Long acctId;

    private Long acctBalanceId;

    private Short depositCode;

    private Long oldBalance;

    private Long money;

    private Long newBalance;

    private String accessTag;

    private Date operateTime;

    private String eparchyCode;

    private String cancelTag;

    private Long invoiceFee;

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getAcctBalanceId() {
        return acctBalanceId;
    }

    public void setAcctBalanceId(Long acctBalanceId) {
        this.acctBalanceId = acctBalanceId;
    }

    public Short getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(Short depositCode) {
        this.depositCode = depositCode;
    }

    public Long getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Long oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Long newBalance) {
        this.newBalance = newBalance;
    }

    public String getAccessTag() {
        return accessTag;
    }

    public void setAccessTag(String accessTag) {
        this.accessTag = accessTag == null ? null : accessTag.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getEparchyCode() {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode) {
        this.eparchyCode = eparchyCode == null ? null : eparchyCode.trim();
    }

    public String getCancelTag() {
        return cancelTag;
    }

    public void setCancelTag(String cancelTag) {
        this.cancelTag = cancelTag == null ? null : cancelTag.trim();
    }

    public Long getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(Long invoiceFee) {
        this.invoiceFee = invoiceFee;
    }
}