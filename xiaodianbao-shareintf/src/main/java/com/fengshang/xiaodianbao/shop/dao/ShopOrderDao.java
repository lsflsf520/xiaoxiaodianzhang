package com.fengshang.xiaodianbao.shop.dao;

import com.fengshang.xiaodianbao.shop.entity.ShopOrder;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderDao extends IBaseDao<Integer, ShopOrder> {
    Integer insertReturnPK(ShopOrder shopOrder);
}