package com.fengshang.xiaodianbao.wallet.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fengshang.xiaodianbao.constant.CouponState;
import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.DiscountType;
import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.common.utils.ChineseWorkHolidayUtil;
import com.xyz.tools.db.bean.BaseEntity;

public class UserCoupon extends BaseEntity<Integer> {
	private Integer id;

	private Integer wxUid;

	private Integer shopId;

	private String srcCode;

	private CouponType couponType;

	private String tmplFileName;

	private DiscountType discountType;

	private Double discount;

	private Double minAmount;

	private Bool onlinePay;

	private Bool useInHoliday;

	private Date uneffectTime;

	private Date effectTime;

	private String canUseTime;

	private String remark;

	private CouponState couponState;

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

	public String getSrcCode() {
		return srcCode;
	}

	public void setSrcCode(String srcCode) {
		this.srcCode = srcCode == null ? null : srcCode.trim();
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

	public Bool getOnlinePay() {
		return onlinePay;
	}

	public void setOnlinePay(Bool onlinePay) {
		this.onlinePay = onlinePay;
	}

	public Bool getUseInHoliday() {
		return useInHoliday;
	}

	public void setUseInHoliday(Bool useInHoliday) {
		this.useInHoliday = useInHoliday;
	}

	public Date getUneffectTime() {
		return uneffectTime;
	}

	public void setUneffectTime(Date uneffectTime) {
		this.uneffectTime = uneffectTime;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
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

	public CouponState getCouponState() {
		return couponState;
	}

	public void setCouponState(CouponState couponState) {
		this.couponState = couponState;
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

	/**
	 * 
	 * @return 返回优惠券是否处于可用状态
	 */
	public boolean isAvail() {
		return CouponState.RECVD.equals(this.getCouponState()) && this.getUneffectTime() != null
				&& this.getUneffectTime().getTime() >= System.currentTimeMillis();
	}

	/**
	 * 
	 * @return 返回优惠券在当前时间，当前可用订单消费金额条件下是否可用
	 */
	public boolean canUse(BigDecimal availAmount) {

		return availAmount.compareTo(new BigDecimal(this.getMinAmount())) >= 0 && isAvail()
				&& (Bool.Y.equals(getUseInHoliday()) || !ChineseWorkHolidayUtil.isHoliday(new Date()));
	}

	@Override
	public Integer getPK() {
		return id;
	}
}