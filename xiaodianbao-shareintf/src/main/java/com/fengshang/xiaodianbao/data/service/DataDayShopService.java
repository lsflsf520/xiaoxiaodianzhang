package com.fengshang.xiaodianbao.data.service;

import com.fengshang.xiaodianbao.data.dao.DataDayShopDao;
import com.fengshang.xiaodianbao.data.entity.DataDayShop;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DataDayShopService extends AbstractBaseService<Integer, DataDayShop> {
    @Resource
    private DataDayShopDao dataDayShopDao;

    @Override
    protected IBaseDao<Integer, DataDayShop> getBaseDao() {
        return dataDayShopDao;
    }

    public Integer insertReturnPK(DataDayShop dataDayShop) {
        dataDayShopDao.insertReturnPK(dataDayShop);
        return dataDayShop.getPK();
    }

    public Integer doSave(DataDayShop dataDayShop) {
        if (dataDayShop.getPK() == null) {
            return this.insertReturnPK(dataDayShop);
        }
        this.update(dataDayShop);
        return dataDayShop.getPK();
    }
}