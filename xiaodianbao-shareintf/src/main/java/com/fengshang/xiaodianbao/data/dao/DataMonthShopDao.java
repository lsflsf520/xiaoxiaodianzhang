package com.fengshang.xiaodianbao.data.dao;

import com.fengshang.xiaodianbao.data.entity.DataMonthShop;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DataMonthShopDao extends IBaseDao<Integer, DataMonthShop> {
    Integer insertReturnPK(DataMonthShop dataMonthShop);
}