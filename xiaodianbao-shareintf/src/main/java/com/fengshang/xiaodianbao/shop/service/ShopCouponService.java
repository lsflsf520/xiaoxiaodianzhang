package com.fengshang.xiaodianbao.shop.service;

import com.fengshang.xiaodianbao.shop.dao.ShopCouponDao;
import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShopCouponService extends AbstractBaseService<Integer, ShopCoupon> {
    @Resource
    private ShopCouponDao shopCouponDao;

    @Override
    protected IBaseDao<Integer, ShopCoupon> getBaseDao() {
        return shopCouponDao;
    }

    public Integer insertReturnPK(ShopCoupon shopCoupon) {
        shopCouponDao.insertReturnPK(shopCoupon);
        return shopCoupon.getPK();
    }

    public Integer doSave(ShopCoupon shopCoupon) {
        if (shopCoupon.getPK() == null) {
            return this.insertReturnPK(shopCoupon);
        }
        this.update(shopCoupon);
        return shopCoupon.getPK();
    }
}