package com.fengshang.xiaodianbao.data.dao;

import com.fengshang.xiaodianbao.data.entity.DataDayShop;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDayShopDao extends IBaseDao<Integer, DataDayShop> {
    Integer insertReturnPK(DataDayShop dataDayShop);
}