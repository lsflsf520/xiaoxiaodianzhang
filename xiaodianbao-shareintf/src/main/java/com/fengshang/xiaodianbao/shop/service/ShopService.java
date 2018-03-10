package com.fengshang.xiaodianbao.shop.service;

import com.fengshang.xiaodianbao.shop.dao.ShopDao;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends AbstractBaseService<Integer, Shop> {
    @Resource
    private ShopDao shopDao;

    @Override
    protected IBaseDao<Integer, Shop> getBaseDao() {
        return shopDao;
    }

    public Integer insertReturnPK(Shop shop) {
        shopDao.insertReturnPK(shop);
        return shop.getPK();
    }

    public Integer doSave(Shop shop) {
        if (shop.getPK() == null) {
            return this.insertReturnPK(shop);
        }
        this.update(shop);
        return shop.getPK();
    }
}