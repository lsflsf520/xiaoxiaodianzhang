package com.fengshang.xiaodianbao.shop.service;

import com.fengshang.xiaodianbao.shop.dao.ShopOrderDao;
import com.fengshang.xiaodianbao.shop.entity.ShopOrder;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderService extends AbstractBaseService<Integer, ShopOrder> {
    @Resource
    private ShopOrderDao shopOrderDao;

    @Override
    protected IBaseDao<Integer, ShopOrder> getBaseDao() {
        return shopOrderDao;
    }

    public Integer insertReturnPK(ShopOrder shopOrder) {
        shopOrderDao.insertReturnPK(shopOrder);
        return shopOrder.getPK();
    }

    public Integer doSave(ShopOrder shopOrder) {
        if (shopOrder.getPK() == null) {
            return this.insertReturnPK(shopOrder);
        }
        this.update(shopOrder);
        return shopOrder.getPK();
    }
}