package com.fengshang.xiaodianbao.data.entity;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.xyz.tools.db.bean.BaseEntity;
import java.util.Date;

public class DataMonthShop extends BaseEntity<Integer> {
    private Integer id;

    private Date dataDay;

    private Integer shopId;

    private CouponType couponType;

    private Integer recvedNum;

    private Integer usedNum;

    private Integer newUserNum;

    private Integer oldUserNum;

    private Double orderFee;

    private Double couponFee;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataDay() {
        return dataDay;
    }

    public void setDataDay(Date dataDay) {
        this.dataDay = dataDay;
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

    public Integer getRecvedNum() {
        return recvedNum;
    }

    public void setRecvedNum(Integer recvedNum) {
        this.recvedNum = recvedNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public Integer getNewUserNum() {
        return newUserNum;
    }

    public void setNewUserNum(Integer newUserNum) {
        this.newUserNum = newUserNum;
    }

    public Integer getOldUserNum() {
        return oldUserNum;
    }

    public void setOldUserNum(Integer oldUserNum) {
        this.oldUserNum = oldUserNum;
    }

    public Double getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    public Double getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Double couponFee) {
        this.couponFee = couponFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Integer getPK() {
        return id;
    }
}