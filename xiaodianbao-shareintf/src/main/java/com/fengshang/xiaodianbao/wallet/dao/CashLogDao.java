package com.fengshang.xiaodianbao.wallet.dao;

import com.fengshang.xiaodianbao.wallet.entity.CashLog;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CashLogDao extends IBaseDao<Integer, CashLog> {
    Integer insertReturnPK(CashLog cashLog);
}