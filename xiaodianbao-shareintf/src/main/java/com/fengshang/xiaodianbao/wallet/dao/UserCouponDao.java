package com.fengshang.xiaodianbao.wallet.dao;

import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponDao extends IBaseDao<Integer, UserCoupon> {
    Integer insertReturnPK(UserCoupon userCoupon);
}