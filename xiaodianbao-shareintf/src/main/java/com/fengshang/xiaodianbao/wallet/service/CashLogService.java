package com.fengshang.xiaodianbao.wallet.service;

import com.fengshang.xiaodianbao.wallet.dao.CashLogDao;
import com.fengshang.xiaodianbao.wallet.entity.CashLog;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CashLogService extends AbstractBaseService<Integer, CashLog> {
    @Resource
    private CashLogDao cashLogDao;

    @Override
    protected IBaseDao<Integer, CashLog> getBaseDao() {
        return cashLogDao;
    }

    public Integer insertReturnPK(CashLog cashLog) {
        cashLogDao.insertReturnPK(cashLog);
        return cashLog.getPK();
    }

    public Integer doSave(CashLog cashLog) {
        if (cashLog.getPK() == null) {
            return this.insertReturnPK(cashLog);
        }
        this.update(cashLog);
        return cashLog.getPK();
    }
}