package com.fengshang.xiaodianbao.uc.entity;

import java.util.Date;

import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.db.bean.BaseEntity;

public class WxUserExtra extends BaseEntity<Integer> {
	private Integer wxUid;

	private Integer countryId;

	private Integer provinceId;

	private Integer cityId;

	private Integer countyId;

	private String detailAddr;

	private Double lng;

	private Double lat;

	private Bool servMsg;

	private Bool smsMsg;

	private Date createTime;

	private Date lastUptime;

	public Integer getWxUid() {
		return wxUid;
	}

	public void setWxUid(Integer wxUid) {
		this.wxUid = wxUid;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Bool getServMsg() {
		return servMsg;
	}

	public void setServMsg(Bool servMsg) {
		this.servMsg = servMsg;
	}

	public Bool getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(Bool smsMsg) {
		this.smsMsg = smsMsg;
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
		return wxUid;
	}
}