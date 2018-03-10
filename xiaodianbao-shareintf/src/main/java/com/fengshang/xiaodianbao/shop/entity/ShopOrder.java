package com.fengshang.xiaodianbao.shop.entity;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.xyz.tools.common.constant.OrdState;
import com.xyz.tools.common.constant.PayMethod;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrder extends BaseEntity<Integer> {
    private Integer id;

    private Integer shopId;

    private Integer buyNum;

    private Integer sendNum;

    private BigDecimal payFee;

    private PayMethod payMethod;

    private BigDecimal balancePayFee;

    private BigDecimal thirdPayFee;

    private String thirdTradeId;

    private String thirdPaymentNo;

    private CouponType couponType;

    private OrdState ordState;

    private Date createTime;

    private Date lastUptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getBalancePayFee() {
        return balancePayFee;
    }

    public void setBalancePayFee(BigDecimal balancePayFee) {
        this.balancePayFee = balancePayFee;
    }

    public BigDecimal getThirdPayFee() {
        return thirdPayFee;
    }

    public void setThirdPayFee(BigDecimal thirdPayFee) {
        this.thirdPayFee = thirdPayFee;
    }

    public String getThirdTradeId() {
        return thirdTradeId;
    }

    public void setThirdTradeId(String thirdTradeId) {
        this.thirdTradeId = thirdTradeId == null ? null : thirdTradeId.trim();
    }

    public String getThirdPaymentNo() {
        return thirdPaymentNo;
    }

    public void setThirdPaymentNo(String thirdPaymentNo) {
        this.thirdPaymentNo = thirdPaymentNo == null ? null : thirdPaymentNo.trim();
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public OrdState getOrdState() {
        return ordState;
    }

    public void setOrdState(OrdState ordState) {
        this.ordState = ordState;
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