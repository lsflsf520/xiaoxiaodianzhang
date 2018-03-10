package com.fengshang.xiaodianbao.wallet.service;

import com.fengshang.xiaodianbao.wallet.dao.ChargeLogDao;
import com.fengshang.xiaodianbao.wallet.entity.ChargeLog;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChargeLogService extends AbstractBaseService<Integer, ChargeLog> {
    @Resource
    private ChargeLogDao chargeLogDao;

    @Override
    protected IBaseDao<Integer, ChargeLog> getBaseDao() {
        return chargeLogDao;
    }

    public Integer insertReturnPK(ChargeLog chargeLog) {
        chargeLogDao.insertReturnPK(chargeLog);
        return chargeLog.getPK();
    }

    public Integer doSave(ChargeLog chargeLog) {
        if (chargeLog.getPK() == null) {
            return this.insertReturnPK(chargeLog);
        }
        this.update(chargeLog);
        return chargeLog.getPK();
    }
}