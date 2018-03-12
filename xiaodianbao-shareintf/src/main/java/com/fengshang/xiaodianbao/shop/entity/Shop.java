package com.fengshang.xiaodianbao.shop.entity;

import com.xyz.tools.common.constant.CheckState;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class Shop extends BaseEntity<Integer> {
    private Integer id;

    private String fullName;

    private String alias;

    private String logo;

    private Integer indId;

    private Integer subIndId;

    private String mgrName;

    private String kefu;

    private String licence;

    private String defBgImg;

    private Integer provinceId;

    private Integer cityId;

    private Integer countyId;

    private String detailAddr;

    private BigDecimal lng;

    private BigDecimal lat;

    private Integer wxUid;

    private String snapshot;

    private CheckState checkState;

    private Date createTime;

    private Date lastUptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getIndId() {
        return indId;
    }

    public void setIndId(Integer indId) {
        this.indId = indId;
    }

    public Integer getSubIndId() {
        return subIndId;
    }

    public void setSubIndId(Integer subIndId) {
        this.subIndId = subIndId;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName == null ? null : mgrName.trim();
    }

    public String getKefu() {
        return kefu;
    }

    public void setKefu(String kefu) {
        this.kefu = kefu == null ? null : kefu.trim();
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence == null ? null : licence.trim();
    }

    public String getDefBgImg() {
        return defBgImg;
    }

    public void setDefBgImg(String defBgImg) {
        this.defBgImg = defBgImg == null ? null : defBgImg.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public Integer getWxUid() {
        return wxUid;
    }

    public void setWxUid(Integer wxUid) {
        this.wxUid = wxUid;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot == null ? null : snapshot.trim();
    }

    public CheckState getCheckState() {
        return checkState;
    }

    public void setCheckState(CheckState checkState) {
        this.checkState = checkState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUptime() {
        return lastUptime;
    }

    public void setLastUptime(Date lastUptime) {
        this.lastUptime = lastUptime;
    }

    @Override
    public Integer getPK() {
        return id;
    }
    
    @Override
    public int hashCode() {
    	    int hashCode = 17;
        if(this.getId() != null) {
        	    hashCode *= this.getId();
        }
    	    return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
    	    
    	    return obj instanceof Shop && ((Shop)obj).getId() != null && ((Shop)obj).getId().equals(this.getId());
    }
}