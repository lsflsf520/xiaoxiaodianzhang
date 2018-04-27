package com.fengshang.xiaodianbao.uc.dao;

import com.fengshang.xiaodianbao.uc.entity.UserCouponNum;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponNumDao extends IBaseDao<Integer, UserCouponNum> {
    Integer insertReturnPK(UserCouponNum userCouponNum);
}