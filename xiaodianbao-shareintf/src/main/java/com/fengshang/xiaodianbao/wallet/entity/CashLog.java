package com.fengshang.xiaodianbao.wallet.entity;

import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class CashLog extends BaseEntity<Integer> {
    private Integer id;

    private Integer wxUid;

    private BigDecimal cashAmount;

    private BigDecimal preAmount;

    private BigDecimal afterAmount;

    private BigDecimal lastCashLeftAmount;

    private String thirdTradeId;

    private String thirdPaymentNo;

    private String thirdResultCode;

    private String snapshot;

    private String status;

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

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(BigDecimal preAmount) {
        this.preAmount = preAmount;
    }

    public BigDecimal getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(BigDecimal afterAmount) {
        this.afterAmount = afterAmount;
    }

    public BigDecimal getLastCashLeftAmount() {
        return lastCashLeftAmount;
    }

    public void setLastCashLeftAmount(BigDecimal lastCashLeftAmount) {
        this.lastCashLeftAmount = lastCashLeftAmount;
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

    public String getThirdResultCode() {
        return thirdResultCode;
    }

    public void setThirdResultCode(String thirdResultCode) {
        this.thirdResultCode = thirdResultCode == null ? null : thirdResultCode.trim();
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot == null ? null : snapshot.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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