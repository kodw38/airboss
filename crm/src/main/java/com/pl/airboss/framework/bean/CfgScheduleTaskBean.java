package com.pl.airboss.framework.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

    public class CfgScheduleTaskBean {
        private Integer recId;

        private String taskCode;

        private String taskType;

        private String cron;

        private String taskClass;

        private String manageClass;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createDate;

        private String remark;

        private Integer state;

        private Integer isAppInitRun;

        public Integer getRecId() {
            return recId;
        }

        public void setRecId(Integer recId) {
            this.recId = recId;
        }

        public String getTaskCode() {
            return taskCode;
        }

        public void setTaskCode(String taskCode) {
            this.taskCode = taskCode == null ? null : taskCode.trim();
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType == null ? null : taskType.trim();
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron == null ? null : cron.trim();
        }

        public String getTaskClass() {
            return taskClass;
        }

        public void setTaskClass(String taskClass) {
            this.taskClass = taskClass == null ? null : taskClass.trim();
        }

        public String getManageClass() {
            return manageClass;
        }

        public void setManageClass(String manageClass) {
            this.manageClass = manageClass == null ? null : manageClass.trim();
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? null : remark.trim();
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getIsAppInitRun() {
            return isAppInitRun;
        }

        public void setIsAppInitRun(Integer isAppInitRun) {
            this.isAppInitRun = isAppInitRun;
        }
    }
