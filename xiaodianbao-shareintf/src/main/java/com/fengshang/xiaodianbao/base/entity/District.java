package com.fengshang.xiaodianbao.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.tools.common.bean.AbstractTreeBean;
import java.util.Date;

public class District extends AbstractTreeBean<Integer, District> {
    private Integer id;

    private String name;

    private String shortName;

    private Integer parentId;

    private String cityLevel;

    private String zoneNum;

    private String postCode;

    private String xzCode;

    private String pinyin;

    private String quanpin;
    
    private Integer priority;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonIgnore
    public String getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel == null ? null : cityLevel.trim();
    }

    @JsonIgnore
    public String getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(String zoneNum) {
        this.zoneNum = zoneNum == null ? null : zoneNum.trim();
    }

    @JsonIgnore
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    @JsonIgnore
    public String getXzCode() {
        return xzCode;
    }

    public void setXzCode(String xzCode) {
        this.xzCode = xzCode == null ? null : xzCode.trim();
    }

    @JsonIgnore
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    @JsonIgnore
    public String getQuanpin() {
        return quanpin;
    }

    public void setQuanpin(String quanpin) {
        this.quanpin = quanpin == null ? null : quanpin.trim();
    }
    
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    @JsonIgnore
    public Integer getPK() {
        return id;
    }
}