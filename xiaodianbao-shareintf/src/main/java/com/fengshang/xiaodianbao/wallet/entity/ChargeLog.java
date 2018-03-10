package com.fengshang.xiaodianbao.wallet.entity;

import com.xyz.tools.common.constant.OrdState;
import com.xyz.tools.common.constant.PayMethod;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class ChargeLog extends BaseEntity<Integer> {
    private Integer id;

    private Integer wxUid;

    private BigDecimal chargeAmount;

    private BigDecimal preAmount;

    private PayMethod payMethod;

    private String thirdTradeId;

    private String thirdPaymentNo;

    private String snapshot;

    private OrdState ordState;

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

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public BigDecimal getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(BigDecimal preAmount) {
        this.preAmount = preAmount;
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

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot == null ? null : snapshot.trim();
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