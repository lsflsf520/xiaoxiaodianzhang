package com.fengshang.xiaodianbao.wallet.service;

import com.fengshang.xiaodianbao.wallet.dao.UserCouponDao;
import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserCouponService extends AbstractBaseService<Integer, UserCoupon> {
    @Resource
    private UserCouponDao userCouponDao;

    @Override
    protected IBaseDao<Integer, UserCoupon> getBaseDao() {
        return userCouponDao;
    }

    public Integer insertReturnPK(UserCoupon userCoupon) {
        userCouponDao.insertReturnPK(userCoupon);
        return userCoupon.getPK();
    }

    public Integer doSave(UserCoupon userCoupon) {
        if (userCoupon.getPK() == null) {
            return this.insertReturnPK(userCoupon);
        }
        this.update(userCoupon);
        return userCoupon.getPK();
    }
}