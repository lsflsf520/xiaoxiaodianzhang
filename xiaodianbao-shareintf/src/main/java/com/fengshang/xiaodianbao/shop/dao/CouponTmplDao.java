package com.fengshang.xiaodianbao.shop.dao;

import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponTmplDao extends IBaseDao<Integer, CouponTmpl> {
    Integer insertReturnPK(CouponTmpl couponTmpl);
}