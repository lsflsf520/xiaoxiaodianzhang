package com.fengshang.xiaodianbao.shop.entity;

import java.util.Date;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.DiscountType;
import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.BaseEntity;

public class ShopCoupon extends BaseEntity<Integer> {
	private Integer id;

	private Integer wxUid;

	private Integer shopId;

	private CouponType couponType;

	private String tmplFileName;

	private DiscountType discountType;

	private Double discount;

	private Double minAmount;

	private Integer dayLimitNum;

	private Integer personLimitNum;

	private Integer dayRecvLimitNum;

	private Bool recvAfterUsed;

	private Integer effectDay;

	private Bool onlinePay;

	private Integer effectAfterRecvDay;

	private Bool useInHoliday;

	private String canUseTime;

	private String remark;

	private CommonStatus status;

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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public String getTmplFileName() {
		return tmplFileName;
	}

	public void setTmplFileName(String tmplFileName) {
		this.tmplFileName = tmplFileName == null ? null : tmplFileName.trim();
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getDayLimitNum() {
		return dayLimitNum;
	}

	public void setDayLimitNum(Integer dayLimitNum) {
		this.dayLimitNum = dayLimitNum;
	}

	public Integer getPersonLimitNum() {
		return personLimitNum;
	}

	public void setPersonLimitNum(Integer personLimitNum) {
		this.personLimitNum = personLimitNum;
	}

	public Integer getDayRecvLimitNum() {
		return dayRecvLimitNum;
	}

	public void setDayRecvLimitNum(Integer dayRecvLimitNum) {
		this.dayRecvLimitNum = dayRecvLimitNum;
	}

	public Bool getRecvAfterUsed() {
		return recvAfterUsed;
	}

	public void setRecvAfterUsed(Bool recvAfterUsed) {
		this.recvAfterUsed = recvAfterUsed;
	}

	public Integer getEffectDay() {
		return effectDay;
	}

	public void setEffectDay(Integer effectDay) {
		this.effectDay = effectDay;
	}

	public Bool getOnlinePay() {
		return onlinePay;
	}

	public void setOnlinePay(Bool onlinePay) {
		this.onlinePay = onlinePay;
	}

	public Integer getEffectAfterRecvDay() {
		return effectAfterRecvDay;
	}

	public void setEffectAfterRecvDay(Integer effectAfterRecvDay) {
		this.effectAfterRecvDay = effectAfterRecvDay;
	}

	public Bool getUseInHoliday() {
		return useInHoliday;
	}

	public void setUseInHoliday(Bool useInHoliday) {
		this.useInHoliday = useInHoliday;
	}

	public String getCanUseTime() {
		return canUseTime;
	}

	public void setCanUseTime(String canUseTime) {
		this.canUseTime = canUseTime == null ? null : canUseTime.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public CommonStatus getStatus() {
		return status;
	}

	public void setStatus(CommonStatus status) {
		this.status = status;
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