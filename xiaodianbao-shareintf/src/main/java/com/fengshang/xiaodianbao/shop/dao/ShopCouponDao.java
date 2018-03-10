package com.fengshang.xiaodianbao.shop.dao;

import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCouponDao extends IBaseDao<Integer, ShopCoupon> {
    Integer insertReturnPK(ShopCoupon shopCoupon);
}