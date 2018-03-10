package com.fengshang.xiaodianbao.shop.dao;

import com.fengshang.xiaodianbao.shop.entity.ShopCouponNum;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCouponNumDao extends IBaseDao<Integer, ShopCouponNum> {
    Integer insertReturnPK(ShopCouponNum shopCouponNum);
}