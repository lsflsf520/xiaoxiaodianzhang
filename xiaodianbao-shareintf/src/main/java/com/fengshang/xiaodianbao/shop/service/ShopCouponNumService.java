package com.fengshang.xiaodianbao.shop.service;

import com.fengshang.xiaodianbao.shop.dao.ShopCouponNumDao;
import com.fengshang.xiaodianbao.shop.entity.ShopCouponNum;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShopCouponNumService extends AbstractBaseService<Integer, ShopCouponNum> {
    @Resource
    private ShopCouponNumDao shopCouponNumDao;

    @Override
    protected IBaseDao<Integer, ShopCouponNum> getBaseDao() {
        return shopCouponNumDao;
    }

    public Integer insertReturnPK(ShopCouponNum shopCouponNum) {
        shopCouponNumDao.insertReturnPK(shopCouponNum);
        return shopCouponNum.getPK();
    }

    public Integer doSave(ShopCouponNum shopCouponNum) {
        if (shopCouponNum.getPK() == null) {
            return this.insertReturnPK(shopCouponNum);
        }
        this.update(shopCouponNum);
        return shopCouponNum.getPK();
    }
}