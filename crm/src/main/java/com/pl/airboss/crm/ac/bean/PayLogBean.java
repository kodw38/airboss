package com.pl.airboss.crm.ac.bean;

import java.util.Date;

public class PayLogBean extends PayLogBeanKey {
    private Long rowid;

    private String eparchyCode;

    private String cityCode;

    private Long custId;

    private Long userId;

    private String serialNumber;

    private String netTypeCode;

    private Long acctId;

    private String channelId;

    private Integer paymentId;

    private Short payFeeModeCode;

    private Integer paymentOp;

    private Long recvFee;

    private Long limitMoney;

    private Date recvTime;

    private String recvEparchyCode;

    private String recvCityCode;

    private String recvDepartId;

    private String recvStaffId;

    private Integer paymentReasonCode;

    private String inputNo;

    private Integer inputMode;

    private String outerTradeId;

    private String actTag;

    private String extendTag;

    private Integer actionCode;

    private Long actionEventId;

    private Integer paymentRuleId;

    private String remark;

    private String cancelTag;

    private String cancelStaffId;

    private String cancelDepartId;

    private String cancelCityCode;

    private String cancelEparchyCode;

    private Date cancelTime;

    private Long cancelChargeId;

    private Long rsrvFee1;

    private Long rsrvFee2;

    private String rsrvInfo1;

    private String rsrvInfo2;

    private Long rsrvNum1;

    private Long rsrvNum2;

    private String rsrvInfo3;

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getEparchyCode() {
        return eparchyCode;
    }

    public void setEparchyCode(String eparchyCode) {
        this.eparchyCode = eparchyCode == null ? null : eparchyCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getNetTypeCode() {
        return netTypeCode;
    }

    public void setNetTypeCode(String netTypeCode) {
        this.netTypeCode = netTypeCode == null ? null : netTypeCode.trim();
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Short getPayFeeModeCode() {
        return payFeeModeCode;
    }

    public void setPayFeeModeCode(Short payFeeModeCode) {
        this.payFeeModeCode = payFeeModeCode;
    }

    public Integer getPaymentOp() {
        return paymentOp;
    }

    public void setPaymentOp(Integer paymentOp) {
        this.paymentOp = paymentOp;
    }

    public Long getRecvFee() {
        return recvFee;
    }

    public void setRecvFee(Long recvFee) {
        this.recvFee = recvFee;
    }

    public Long getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(Long limitMoney) {
        this.limitMoney = limitMoney;
    }

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public String getRecvEparchyCode() {
        return recvEparchyCode;
    }

    public void setRecvEparchyCode(String recvEparchyCode) {
        this.recvEparchyCode = recvEparchyCode == null ? null : recvEparchyCode.trim();
    }

    public String getRecvCityCode() {
        return recvCityCode;
    }

    public void setRecvCityCode(String recvCityCode) {
        this.recvCityCode = recvCityCode == null ? null : recvCityCode.trim();
    }

    public String getRecvDepartId() {
        return recvDepartId;
    }

    public void setRecvDepartId(String recvDepartId) {
        this.recvDepartId = recvDepartId == null ? null : recvDepartId.trim();
    }

    public String getRecvStaffId() {
        return recvStaffId;
    }

    public void setRecvStaffId(String recvStaffId) {
        this.recvStaffId = recvStaffId == null ? null : recvStaffId.trim();
    }

    public Integer getPaymentReasonCode() {
        return paymentReasonCode;
    }

    public void setPaymentReasonCode(Integer paymentReasonCode) {
        this.paymentReasonCode = paymentReasonCode;
    }

    public String getInputNo() {
        return inputNo;
    }

    public void setInputNo(String inputNo) {
        this.inputNo = inputNo == null ? null : inputNo.trim();
    }

    public Integer getInputMode() {
        return inputMode;
    }

    public void setInputMode(Integer inputMode) {
        this.inputMode = inputMode;
    }

    public String getOuterTradeId() {
        return outerTradeId;
    }

    public void setOuterTradeId(String outerTradeId) {
        this.outerTradeId = outerTradeId == null ? null : outerTradeId.trim();
    }

    public String getActTag() {
        return actTag;
    }

    public void setActTag(String actTag) {
        this.actTag = actTag == null ? null : actTag.trim();
    }

    public String getExtendTag() {
        return extendTag;
    }

    public void setExtendTag(String extendTag) {
        this.extendTag = extendTag == null ? null : extendTag.trim();
    }

    public Integer getActionCode() {
        return actionCode;
    }

    public void setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
    }

    public Long getActionEventId() {
        return actionEventId;
    }

    public void setActionEventId(Long actionEventId) {
        this.actionEventId = actionEventId;
    }

    public Integer getPaymentRuleId() {
        return paymentRuleId;
    }

    public void setPaymentRuleId(Integer paymentRuleId) {
        this.paymentRuleId = paymentRuleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCancelTag() {
        return cancelTag;
    }

    public void setCancelTag(String cancelTag) {
        this.cancelTag = cancelTag == null ? null : cancelTag.trim();
    }

    public String getCancelStaffId() {
        return cancelStaffId;
    }

    public void setCancelStaffId(String cancelStaffId) {
        this.cancelStaffId = cancelStaffId == null ? null : cancelStaffId.trim();
    }

    public String getCancelDepartId() {
        return cancelDepartId;
    }

    public void setCancelDepartId(String cancelDepartId) {
        this.cancelDepartId = cancelDepartId == null ? null : cancelDepartId.trim();
    }

    public String getCancelCityCode() {
        return cancelCityCode;
    }

    public void setCancelCityCode(String cancelCityCode) {
        this.cancelCityCode = cancelCityCode == null ? null : cancelCityCode.trim();
    }

    public String getCancelEparchyCode() {
        return cancelEparchyCode;
    }

    public void setCancelEparchyCode(String cancelEparchyCode) {
        this.cancelEparchyCode = cancelEparchyCode == null ? null : cancelEparchyCode.trim();
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Long getCancelChargeId() {
        return cancelChargeId;
    }

    public void setCancelChargeId(Long cancelChargeId) {
        this.cancelChargeId = cancelChargeId;
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

    public Long getRsrvNum1() {
        return rsrvNum1;
    }

    public void setRsrvNum1(Long rsrvNum1) {
        this.rsrvNum1 = rsrvNum1;
    }

    public Long getRsrvNum2() {
        return rsrvNum2;
    }

    public void setRsrvNum2(Long rsrvNum2) {
        this.rsrvNum2 = rsrvNum2;
    }

    public String getRsrvInfo3() {
        return rsrvInfo3;
    }

    public void setRsrvInfo3(String rsrvInfo3) {
        this.rsrvInfo3 = rsrvInfo3 == null ? null : rsrvInfo3.trim();
    }
}