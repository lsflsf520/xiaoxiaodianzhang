package com.fengshang.xiaodianbao.shop.dao;

import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDao extends IBaseDao<Integer, Shop> {
    Integer insertReturnPK(Shop shop);
}