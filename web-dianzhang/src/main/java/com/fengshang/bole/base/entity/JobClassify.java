package com.fengshang.bole.base.entity;

import com.ujigu.secure.common.bean.BaseEntity;
import com.ujigu.secure.common.bean.CommonStatus;
import java.util.Date;

public class JobClassify extends BaseEntity<Integer> {
    private Integer id;

    private String name;

    private Integer hangyeId;

    private CommonStatus status;

    private Date createTime;

    private Date lastUptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getHangyeId() {
        return hangyeId;
    }

    public void setHangyeId(Integer hangyeId) {
        this.hangyeId = hangyeId;
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