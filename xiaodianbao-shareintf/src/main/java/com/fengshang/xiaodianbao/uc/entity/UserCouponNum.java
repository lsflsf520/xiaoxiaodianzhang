package com.fengshang.xiaodianbao.uc.entity;

import java.util.Date;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.xyz.tools.db.bean.BaseEntity;

public class UserCouponNum extends BaseEntity<Integer> {
	private Integer id;

	private Integer wxUid;

	private Integer freeLeftNum;

	private Integer buyLeftNum;

	private Integer buyTotalNum;

	private CouponType couponType;

	private Date createTime;

	private Date lastUptime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWxUid() {
		return wxUid;
	}

	public void setWxUid(Integer wxUid) {
		this.wxUid = wxUid;
	}

	public Integer getFreeLeftNum() {
		return freeLeftNum;
	}

	public void setFreeLeftNum(Integer freeLeftNum) {
		this.freeLeftNum = freeLeftNum;
	}

	public Integer getBuyLeftNum() {
		return buyLeftNum;
	}

	public Integer getLeftNum() {
		return (this.buyLeftNum == null ? 0 : this.getBuyLeftNum())
				+ (this.freeLeftNum == null ? 0 : this.getFreeLeftNum());
	}

	public void setBuyLeftNum(Integer buyLeftNum) {
		this.buyLeftNum = buyLeftNum;
	}

	public Integer getBuyTotalNum() {
		return buyTotalNum;
	}

	public void setBuyTotalNum(Integer buyTotalNum) {
		this.buyTotalNum = buyTotalNum;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
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
}