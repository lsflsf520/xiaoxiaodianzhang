package com.fengshang.xiaodianbao.wallet.entity;

import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.BaseEntity;
import java.util.Date;

public class Wallet extends BaseEntity<Integer> {
    private Integer wxUid;

    private Double balance;

    private Double totalChargeAmount;

    private CommonStatus status;

    private Date createTime;

    private Date lastUptime;

    public Integer getWxUid() {
        return wxUid;
    }

    public void setWxUid(Integer wxUid) {
        this.wxUid = wxUid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalChargeAmount() {
        return totalChargeAmount;
    }

    public void setTotalChargeAmount(Double totalChargeAmount) {
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