package com.sdcsoft.datamanage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdcsoft.datamanage.utils.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Device implements Serializable {

    public static final int STATUS_SELL = 1;//已售出
    public static final int STATUS_READY = 0;//未售出

    private Integer id;
    private Integer enterpriseId,areaId,power,media,manufacturerId,status,runStatus;
    private String deviceNo,deviceType,enterpriseName,devicePrefix,deviceSuffix,customerNo,customerName;
    private Timestamp importDatetime;//导入时间
    private Timestamp saleDatetime; //售出时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDeviceSuffix() {
        return deviceSuffix;
    }

    public void setDeviceSuffix(String deviceSuffix) {
        this.deviceSuffix = deviceSuffix;
    }

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    public Timestamp getImportDatetime() {
        return importDatetime;
    }
    public void setImportDatetime(Timestamp importDatetime) {
        this.importDatetime = importDatetime;
    }
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    public Timestamp getSaleDatetime() {
        return saleDatetime;
    }
    public void setSaleDatetime(Timestamp saleDatetime) {
        this.saleDatetime = saleDatetime;
    }


    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDevicePrefix() {
        return devicePrefix;
    }

    public void setDevicePrefix(String devicePrefix) {
        this.devicePrefix = devicePrefix;
    }
}
