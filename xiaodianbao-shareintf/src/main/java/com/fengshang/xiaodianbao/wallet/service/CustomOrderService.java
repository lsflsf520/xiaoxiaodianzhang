package com.fengshang.xiaodianbao.wallet.service;

import com.fengshang.xiaodianbao.wallet.dao.CustomOrderDao;
import com.fengshang.xiaodianbao.wallet.entity.CustomOrder;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomOrderService extends AbstractBaseService<Integer, CustomOrder> {
    @Resource
    private CustomOrderDao customOrderDao;

    @Override
    protected IBaseDao<Integer, CustomOrder> getBaseDao() {
        return customOrderDao;
    }

    public Integer insertReturnPK(CustomOrder customOrder) {
        customOrderDao.insertReturnPK(customOrder);
        return customOrder.getPK();
    }

    public Integer doSave(CustomOrder customOrder) {
        if (customOrder.getPK() == null) {
            return this.insertReturnPK(customOrder);
        }
        this.update(customOrder);
        return customOrder.getPK();
    }
}