package com.pl.airboss.crm.res.bean;

public class ResPatternDefineQueryRspBean extends ResPatternDefineBean {
    private Long resSpecId;
    private String modeDesc;

    public Long getResSpecId() {
        return resSpecId;
    }

    public void setResSpecId(Long resSpecId) {
        this.resSpecId = resSpecId;
    }

    public String getModeDesc() {
        return modeDesc;
    }

    public void setModeDesc(String modeDesc) {
        this.modeDesc = modeDesc;
    }
}
