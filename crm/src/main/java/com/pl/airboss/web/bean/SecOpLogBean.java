package com.pl.airboss.web.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pl.airboss.framework.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Map;

public class SecOpLogBean {
    /** 日志主键 */
    @Excel(name = "操作序号", cellType = Excel.ColumnType.NUMERIC)
    private Integer recId;
    /** 操作模块 */
    @Excel(name = "操作模块")
    private String title;
    /** 业务类型（0其它 1新增 2修改 3删除） */
    @Excel(name = "业务类型", readConverterExp = "0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据")
    private String businessType;
    /** 请求方法 */
    @Excel(name = "请求方法")
    private String method;

    private String[] businessTypes;

    public String[] getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(String[] businessTypes) {
        this.businessTypes = businessTypes;
    }
    @Excel(name = "请求方式")
    private String requestMethod;
    @Excel(name = "操作类别", readConverterExp = "0=其它,1=后台用户,2=手机端用户")
    private String operatorType;
    @Excel(name = "操作人员")
    private String operName;
    @Excel(name = "请求地址")
    private String operUrl;
    @Excel(name = "操作地址")
    private String operIp;
    @Excel(name = "请求参数")
    private String operParam;
    @Excel(name = "返回参数")
    private String jsonResult;
    @Excel(name = "错误消息")
    private String errorMsg;
    @Excel(name = "操作地点")
    private String operLocation;
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date doneDate;

    private String remark;
    @Excel(name = "状态", readConverterExp = "1=正常,0=异常")
    private Integer status;

    private Map params;

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl == null ? null : operUrl.trim();
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp == null ? null : operIp.trim();
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam == null ? null : operParam.trim();
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult == null ? null : jsonResult.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation == null ? null : operLocation.trim();
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("operId",getRecId())
                .append("title", getTitle())
                .append("businessType", getBusinessType())
                .append("businessTypes", getBusinessTypes())
                .append("method", getMethod())
                .append("requestMethod", getRequestMethod())
                .append("operatorType", getOperatorType())
                .append("operName", getOperName())
                .append("operUrl", getOperUrl())
                .append("operIp", getOperIp())
                .append("operLocation", getOperLocation())
                .append("operParam", getOperParam())
                .append("status", getStatus())
                .append("errorMsg", getErrorMsg())
                .append("operTime",getDoneDate())
                .toString();
    }
}