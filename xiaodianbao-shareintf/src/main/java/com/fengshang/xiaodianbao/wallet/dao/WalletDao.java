package com.fengshang.xiaodianbao.wallet.dao;

import com.fengshang.xiaodianbao.wallet.entity.Wallet;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends IBaseDao<Integer, Wallet> {
    Integer insertReturnPK(Wallet wallet);
}