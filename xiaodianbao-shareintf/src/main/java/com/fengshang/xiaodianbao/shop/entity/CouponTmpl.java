package com.fengshang.xiaodianbao.shop.entity;

import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class CouponTmpl extends BaseEntity<Integer> {
    private Integer id;

    private String couponType;

    private String tmplFileName;

    private String discountType;

    private BigDecimal discount;

    private BigDecimal minAmount;

    private Integer dayLimitNum;

    private Integer personLimitNum;

    private Integer effectDay;

    private String onlinePay;

    private Integer effectAfterRecvDay;

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

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public String getTmplFileName() {
        return tmplFileName;
    }

    public void setTmplFileName(String tmplFileName) {
        this.tmplFileName = tmplFileName == null ? null : tmplFileName.trim();
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
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

    public Integer getEffectDay() {
        return effectDay;
    }

    public void setEffectDay(Integer effectDay) {
        this.effectDay = effectDay;
    }

    public String getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(String onlinePay) {
        this.onlinePay = onlinePay == null ? null : onlinePay.trim();
    }

    public Integer getEffectAfterRecvDay() {
        return effectAfterRecvDay;
    }

    public void setEffectAfterRecvDay(Integer effectAfterRecvDay) {
        this.effectAfterRecvDay = effectAfterRecvDay;
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