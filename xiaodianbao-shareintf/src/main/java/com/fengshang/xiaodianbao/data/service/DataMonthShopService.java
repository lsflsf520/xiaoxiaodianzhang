package com.fengshang.xiaodianbao.data.service;

import com.fengshang.xiaodianbao.data.dao.DataMonthShopDao;
import com.fengshang.xiaodianbao.data.entity.DataMonthShop;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DataMonthShopService extends AbstractBaseService<Integer, DataMonthShop> {
    @Resource
    private DataMonthShopDao dataMonthShopDao;

    @Override
    protected IBaseDao<Integer, DataMonthShop> getBaseDao() {
        return dataMonthShopDao;
    }

    public Integer insertReturnPK(DataMonthShop dataMonthShop) {
        dataMonthShopDao.insertReturnPK(dataMonthShop);
        return dataMonthShop.getPK();
    }

    public Integer doSave(DataMonthShop dataMonthShop) {
        if (dataMonthShop.getPK() == null) {
            return this.insertReturnPK(dataMonthShop);
        }
        this.update(dataMonthShop);
        return dataMonthShop.getPK();
    }
}