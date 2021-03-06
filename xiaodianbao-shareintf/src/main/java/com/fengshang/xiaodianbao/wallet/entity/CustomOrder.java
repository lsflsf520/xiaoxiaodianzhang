package com.fengshang.xiaodianbao.wallet.entity;

import com.xyz.tools.common.constant.OrdState;
import com.xyz.tools.common.constant.PayMethod;
import com.xyz.tools.db.bean.BaseEntity;
import java.util.Date;

public class CustomOrder extends BaseEntity<Integer> {
    private Integer userCouponId;

    private Integer wxUid;

    private Double ordFee;

    private Double canUseFee;

    private Double leftPayFee;

    private Double balancePayFee;

    private Double thirdPayFee;

    private PayMethod payMethod;

    private String thirdTradeId;

    private String thirdPaymentNo;

    private OrdState ordState;

    private Date createTime;

    private Date lastUptime;

    public Integer getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Integer userCouponId) {
        this.userCouponId = userCouponId;
    }

    public Integer getWxUid() {
        return wxUid;
    }

    public void setWxUid(Integer wxUid) {
        this.wxUid = wxUid;
    }

    public Double getOrdFee() {
        return ordFee;
    }

    public void setOrdFee(Double ordFee) {
        this.ordFee = ordFee;
    }

    public Double getCanUseFee() {
        return canUseFee;
    }

    public void setCanUseFee(Double canUseFee) {
        this.canUseFee = canUseFee;
    }

    public Double getLeftPayFee() {
        return leftPayFee;
    }

    public void setLeftPayFee(Double leftPayFee) {
        this.leftPayFee = leftPayFee;
    }

    public Double getBalancePayFee() {
        return balancePayFee;
    }

    public void setBalancePayFee(Double balancePayFee) {
        this.balancePayFee = balancePayFee;
    }

    public Double getThirdPayFee() {
        return thirdPayFee;
    }

    public void setThirdPayFee(Double thirdPayFee) {
        this.thirdPayFee = thirdPayFee;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
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
        return userCouponId;
    }
}