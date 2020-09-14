package com.pl.airboss.crm.res.bean;

public class ResSelpriceModeBeanKey {
    private Long resSpecId;

    private Long patternId;

    private String regionId;

    public Long getResSpecId() {
        return resSpecId;
    }

    public void setResSpecId(Long resSpecId) {
        this.resSpecId = resSpecId;
    }

    public Long getPatternId() {
        return patternId;
    }

    public void setPatternId(Long patternId) {
        this.patternId = patternId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }
}