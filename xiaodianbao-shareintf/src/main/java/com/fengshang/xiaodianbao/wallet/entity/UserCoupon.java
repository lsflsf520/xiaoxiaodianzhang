package com.fengshang.xiaodianbao.wallet.entity;

import com.fengshang.xiaodianbao.constant.CouponState;
import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.DiscountType;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class UserCoupon extends BaseEntity<Integer> {
    private Integer id;

    private Integer wxUid;

    private Integer shopId;

    private String srcCode;

    private CouponType couponType;

    private String tmplFileName;

    private DiscountType discountType;

    private BigDecimal discount;

    private BigDecimal minAmount;

    private String onlinePay;

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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public String getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(String onlinePay) {
        this.onlinePay = onlinePay == null ? null : onlinePay.trim();
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

    @Override
    public Integer getPK() {
        return id;
    }
}