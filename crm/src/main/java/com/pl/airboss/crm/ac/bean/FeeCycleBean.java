package com.pl.airboss.crm.ac.bean;

public class FeeCycleBean {
    private Short cycleRuleId;

    private String remark;

    private String cycleType;

    private Short cycleNum;

    private String defineTag;

    public Short getCycleRuleId() {
        return cycleRuleId;
    }

    public void setCycleRuleId(Short cycleRuleId) {
        this.cycleRuleId = cycleRuleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.trim();
    }

    public Short getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(Short cycleNum) {
        this.cycleNum = cycleNum;
    }

    public String getDefineTag() {
        return defineTag;
    }

    public void setDefineTag(String defineTag) {
        this.defineTag = defineTag == null ? null : defineTag.trim();
    }
}