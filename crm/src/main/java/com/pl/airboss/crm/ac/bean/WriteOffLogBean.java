package com.pl.airboss.crm.ac.bean;

import java.math.BigDecimal;
import java.util.Date;

public class WriteOffLogBean extends WriteOffLogBeanKey {
    private Long chargeId;

    private Long acctId;

    private Long userId;

    private Integer cycleId;

    private String netTypeCode;

    private Long billId;

    private Integer integrateItemCode;

    private Short depositCode;

    private Long acctBalanceId;

    private Long writeoffFee;

    private Long impFee;

    private Long fee;

    private Long oldBalance;

    private Long newBalance;

    private BigDecimal lateFee;

    private BigDecimal lateBalance;

    private BigDecimal oldLateBalance;

    private BigDecimal newLateBalance;

    private BigDecimal derateLateFee;

    private Date latecalDate;

    private String oldPaytag;

    private String newPaytag;

    private String canPaytag;

    private Date operateTime;

    private String eparchyCode;

    private Short drecvTimes;

    private String cancelTag;

    private Integer depositLimitRuleid;

    private Integer depositPriorRuleid;

    private Integer itemPriorRuleid;

    private Integer userBeginDate;

    private Integer userEndDate;

    private Integer acctBeginDate;

    private Integer acctDay;

    private Long noTaxFee;

    private Long taxFee;

    private Integer taxRate;

    private Long noTaxLatefee;

    private Long taxLatefee;

    private Integer taxLaterate;

    private Integer oldItemId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public String getNetTypeCode() {
        return netTypeCode;
    }

    public void setNetTypeCode(String netTypeCode) {
        this.netTypeCode = netTypeCode == null ? null : netTypeCode.trim();
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getIntegrateItemCode() {
        return integrateItemCode;
    }

    public void setIntegrateItemCode(Integer integrateItemCode) {
        this.integrateItemCode = integrateItemCode;
    }

    public Short getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(Short depositCode) {
        this.depositCode = depositCode;
    }

    public Long getAcctBalanceId() {
        return acctBalanceId;
    }

    public void setAcctBalanceId(Long acctBalanceId) {
        this.acctBalanceId = acctBalanceId;
    }

    public Long getWriteoffFee() {
        return writeoffFee;
    }

    public void setWriteoffFee(Long writeoffFee) {
        this.writeoffFee = writeoffFee;
    }

    public Long getImpFee() {
        return impFee;
    }

    public void setImpFee(Long impFee) {
        this.impFee = impFee;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Long oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Long newBalance) {
        this.newBalance = newBalance;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public BigDecimal getLateBalance() {
        return lateBalance;
    }

    public void setLateBalance(BigDecimal lateBalance) {
        this.lateBalance = lateBalance;
    }

    public BigDecimal getOldLateBalance() {
        return oldLateBalance;
    }

    public void setOldLateBalance(BigDecimal oldLateBalance) {
        this.oldLateBalance = oldLateBalance;
    }

    public BigDecimal getNewLateBalance() {
        return newLateBalance;
    }

    public void setNewLateBalance(BigDecimal newLateBalance) {
        this.newLateBalance = newLateBalance;
    }

    public BigDecimal getDerateLateFee() {
        return derateLateFee;
    }

    public void setDerateLateFee(BigDecimal derateLateFee) {
        this.derateLateFee = derateLateFee;
    }

    public Date getLatecalDate() {
        return latecalDate;
    }

    public void setLatecalDate(Date latecalDate) {
        this.latecalDate = latecalDate;
    }

    public String getOldPaytag() {
        return oldPaytag;
    }

    public void setOldPaytag(String oldPaytag) {
        this.oldPaytag = oldPaytag == null ? null : oldPaytag.trim();
    }

    public String getNewPaytag() {
        return newPaytag;
    }

    public void setNewPaytag(String newPaytag) {
        this.newPaytag = newPaytag == null ? null : newPaytag.trim();
    }

    public String getCanPaytag() {
        return canPaytag;
    }

    public void setCanPaytag(String canPaytag) {
        this.canPaytag = canPaytag == null ? null : canPaytag.trim();
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

    public Short getDrecvTimes() {
        return drecvTimes;
    }

    public void setDrecvTimes(Short drecvTimes) {
        this.drecvTimes = drecvTimes;
    }

    public String getCancelTag() {
        return cancelTag;
    }

    public void setCancelTag(String cancelTag) {
        this.cancelTag = cancelTag == null ? null : cancelTag.trim();
    }

    public Integer getDepositLimitRuleid() {
        return depositLimitRuleid;
    }

    public void setDepositLimitRuleid(Integer depositLimitRuleid) {
        this.depositLimitRuleid = depositLimitRuleid;
    }

    public Integer getDepositPriorRuleid() {
        return depositPriorRuleid;
    }

    public void setDepositPriorRuleid(Integer depositPriorRuleid) {
        this.depositPriorRuleid = depositPriorRuleid;
    }

    public Integer getItemPriorRuleid() {
        return itemPriorRuleid;
    }

    public void setItemPriorRuleid(Integer itemPriorRuleid) {
        this.itemPriorRuleid = itemPriorRuleid;
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

    public Long getNoTaxLatefee() {
        return noTaxLatefee;
    }

    public void setNoTaxLatefee(Long noTaxLatefee) {
        this.noTaxLatefee = noTaxLatefee;
    }

    public Long getTaxLatefee() {
        return taxLatefee;
    }

    public void setTaxLatefee(Long taxLatefee) {
        this.taxLatefee = taxLatefee;
    }

    public Integer getTaxLaterate() {
        return taxLaterate;
    }

    public void setTaxLaterate(Integer taxLaterate) {
        this.taxLaterate = taxLaterate;
    }

    public Integer getOldItemId() {
        return oldItemId;
    }

    public void setOldItemId(Integer oldItemId) {
        this.oldItemId = oldItemId;
    }
}