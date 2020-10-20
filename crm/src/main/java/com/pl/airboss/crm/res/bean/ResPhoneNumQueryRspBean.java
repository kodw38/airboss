package com.pl.airboss.crm.res.bean;

import java.util.Date;

public class ResPhoneNumQueryRspBean extends ResPhoneNumOriginBean {
    /*private String resId;
    private String batchId;
    private Long patternSegId;
    private Long patternDefId;
    private Long opId;
    private String resStatus;
    private String password;
    private String rsrvStr2;
    private Date stockInDate;*/

    private String patternSegName;
    private String netId;
    private String segExp;

    private String patternDefName;
    private String bitRel;

    public String getPatternSegName() {
        return patternSegName;
    }

    public void setPatternSegName(String patternSegName) {
        this.patternSegName = patternSegName;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getSegExp() {
        return segExp;
    }

    public void setSegExp(String segExp) {
        this.segExp = segExp;
    }

    public String getPatternDefName() {
        return patternDefName;
    }

    public void setPatternDefName(String patternDefName) {
        this.patternDefName = patternDefName;
    }

    public String getBitRel() {
        return bitRel;
    }

    public void setBitRel(String bitRel) {
        this.bitRel = bitRel;
    }
}
