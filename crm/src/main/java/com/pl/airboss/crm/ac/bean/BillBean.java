package com.pl.airboss.crm.ac.bean;

import java.util.Date;

public class BillBean {
    private String eparchyCode;

    private String netTypeCode;

    private String serialNumber;

    private Long billId;

    private Long acctId;

    private Long userId;

    private Integer partitionId;

    private Integer cycleId;

    private Integer integrateItemCode;

    private Long fee;

    private Long balance;

    private Long printFee;

    private Long bDiscnt;

    private Long aDiscnt;

    private Long adjustBefore;

    private Long adjustAfter;

    private Long lateFee;

    private Long lateBalance;

    private Date latecalDate;

    private String canpayTag;

    private String payTag;

    private Short versionNo;

    private Date updateTime;

    private String updateDepartId;

    private String updateStaffId;

    private Long chargeId;

    private Long writeoffFee1;

    private Long writeoffFee2;

    private Long writeoffFee3;

    private Long drecvFee2;

    private Long rsrvFee1;

    private Long rsrvFee2;

    private Long rsrvFee3;

    private String rsrvInfo1;

    private String rsrvInfo2;

    private Integer userBeginDate;

    private Integer userEndDate;

    private Integer acctBeginDate;

    private Integer acctDay;

    private Long noTaxFee;

    private Long taxFee;

    private Integer taxRate;

    private Integer mixItemId;

    private Long noTaxBalance;

    private Long taxBalance;

    private Long rowid;

    public String getEparchyCode() {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode) {
        this.eparchyCode = eparchyCode == null ? null : eparchyCode.trim();
    }

    public String getNetTypeCode() {
        return netTypeCode;
    }

    public void setNetTypeCode(String netTypeCode) {
        this.netTypeCode = netTypeCode == null ? null : netTypeCode.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public Integer getIntegrateItemCode() {
        return integrateItemCode;
    }

    public void setIntegrateItemCode(Integer integrateItemCode) {
        this.integrateItemCode = integrateItemCode;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getPrintFee() {
        return printFee;
    }

    public void setPrintFee(Long printFee) {
        this.printFee = printFee;
    }

    public Long getbDiscnt() {
        return bDiscnt;
    }

    public void setbDiscnt(Long bDiscnt) {
        this.bDiscnt = bDiscnt;
    }

    public Long getaDiscnt() {
        return aDiscnt;
    }

    public void setaDiscnt(Long aDiscnt) {
        this.aDiscnt = aDiscnt;
    }

    public Long getAdjustBefore() {
        return adjustBefore;
    }

    public void setAdjustBefore(Long adjustBefore) {
        this.adjustBefore = adjustBefore;
    }

    public Long getAdjustAfter() {
        return adjustAfter;
    }

    public void setAdjustAfter(Long adjustAfter) {
        this.adjustAfter = adjustAfter;
    }

    public Long getLateFee() {
        return lateFee;
    }

    public void setLateFee(Long lateFee) {
        this.lateFee = lateFee;
    }

    public Long getLateBalance() {
        return lateBalance;
    }

    public void setLateBalance(Long lateBalance) {
        this.lateBalance = lateBalance;
    }

    public Date getLatecalDate() {
        return latecalDate;
    }

    public void setLatecalDate(Date latecalDate) {
        this.latecalDate = latecalDate;
    }

    public String getCanpayTag() {
        return canpayTag;
    }

    public void setCanpayTag(String canpayTag) {
        this.canpayTag = canpayTag == null ? null : canpayTag.trim();
    }

    public String getPayTag() {
        return payTag;
    }

    public void setPayTag(String payTag) {
        this.payTag = payTag == null ? null : payTag.trim();
    }

    public Short getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Short versionNo) {
        this.versionNo = versionNo;
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

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Long getWriteoffFee1() {
        return writeoffFee1;
    }

    public void setWriteoffFee1(Long writeoffFee1) {
        this.writeoffFee1 = writeoffFee1;
    }

    public Long getWriteoffFee2() {
        return writeoffFee2;
    }

    public void setWriteoffFee2(Long writeoffFee2) {
        this.writeoffFee2 = writeoffFee2;
    }

    public Long getWriteoffFee3() {
        return writeoffFee3;
    }

    public void setWriteoffFee3(Long writeoffFee3) {
        this.writeoffFee3 = writeoffFee3;
    }

    public Long getDrecvFee2() {
        return drecvFee2;
    }

    public void setDrecvFee2(Long drecvFee2) {
        this.drecvFee2 = drecvFee2;
    }

    public Long getRsrvFee1() {
        return rsrvFee1;
    }

    public void setRsrvFee1(Long rsrvFee1) {
        this.rsrvFee1 = rsrvFee1;
    }

    public Long getRsrvFee2() {
        return rsrvFee2;
    }

    public void setRsrvFee2(Long rsrvFee2) {
        this.rsrvFee2 = rsrvFee2;
    }

    public Long getRsrvFee3() {
        return rsrvFee3;
    }

    public void setRsrvFee3(Long rsrvFee3) {
        this.rsrvFee3 = rsrvFee3;
    }

    public String getRsrvInfo1() {
        return rsrvInfo1;
    }

    public void setRsrvInfo1(String rsrvInfo1) {
        this.rsrvInfo1 = rsrvInfo1 == null ? null : rsrvInfo1.trim();
    }

    public String getRsrvInfo2() {
        return rsrvInfo2;
    }

    public void setRsrvInfo2(String rsrvInfo2) {
        this.rsrvInfo2 = rsrvInfo2 == null ? null : rsrvInfo2.trim();
    }

    public Integer getUserBeginDate() {
        return userBeginDate;
    }

    public void setUserBeginDate(Integer userBeginDate) {
        this.userBeginDate = userBeginDate;
    }

    public Integer getUserEndDate() {
        return userEndDate;
    }

    public void setUserEndDate(Integer userEndDate) {
        this.userEndDate = userEndDate;
    }

    public Integer getAcctBeginDate() {
        return acctBeginDate;
    }

    public void setAcctBeginDate(Integer acctBeginDate) {
        this.acctBeginDate = acctBeginDate;
    }

    public Integer getAcctDay() {
        return acctDay;
    }

    public void setAcctDay(Integer acctDay) {
        this.acctDay = acctDay;
    }

    public Long getNoTaxFee() {
        return noTaxFee;
    }

    public void setNoTaxFee(Long noTaxFee) {
        this.noTaxFee = noTaxFee;
    }

    public Long getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(Long taxFee) {
        this.taxFee = taxFee;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getMixItemId() {
        return mixItemId;
    }

    public void setMixItemId(Integer mixItemId) {
        this.mixItemId = mixItemId;
    }

    public Long getNoTaxBalance() {
        return noTaxBalance;
    }

    public void setNoTaxBalance(Long noTaxBalance) {
        this.noTaxBalance = noTaxBalance;
    }

    public Long getTaxBalance() {
        return taxBalance;
    }

    public void setTaxBalance(Long taxBalance) {
        this.taxBalance = taxBalance;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }
}