package com.fengshang.xiaodianbao.wallet.dao;

import com.fengshang.xiaodianbao.wallet.entity.CustomOrder;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomOrderDao extends IBaseDao<Integer, CustomOrder> {
    Integer insertReturnPK(CustomOrder customOrder);
}