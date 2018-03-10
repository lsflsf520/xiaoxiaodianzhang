package com.fengshang.xiaodianbao.wallet.dao;

import com.fengshang.xiaodianbao.wallet.entity.ChargeLog;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeLogDao extends IBaseDao<Integer, ChargeLog> {
    Integer insertReturnPK(ChargeLog chargeLog);
}