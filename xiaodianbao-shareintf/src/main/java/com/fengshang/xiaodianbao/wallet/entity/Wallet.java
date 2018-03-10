package com.fengshang.xiaodianbao.wallet.entity;

import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class Wallet extends BaseEntity<Integer> {
    private Integer wxUid;

    private BigDecimal balance;

    private BigDecimal totalChargeAmount;

    private CommonStatus status;

    private Date createTime;

    private Date lastUptime;

    public Integer getWxUid() {
        return wxUid;
    }

    public void setWxUid(Integer wxUid) {
        this.wxUid = wxUid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalChargeAmount() {
        return totalChargeAmount;
    }

    public void setTotalChargeAmount(BigDecimal totalChargeAmount) {
        this.totalChargeAmount = totalChargeAmount;
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
        return wxUid;
    }
}