package com.fengshang.xiaodianbao.wallet.service;

import com.fengshang.xiaodianbao.wallet.dao.WalletDao;
import com.fengshang.xiaodianbao.wallet.entity.Wallet;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WalletService extends AbstractBaseService<Integer, Wallet> {
    @Resource
    private WalletDao walletDao;

    @Override
    protected IBaseDao<Integer, Wallet> getBaseDao() {
        return walletDao;
    }

    public Integer insertReturnPK(Wallet wallet) {
        walletDao.insertReturnPK(wallet);
        return wallet.getPK();
    }

    public Integer doSave(Wallet wallet) {
        if (wallet.getPK() == null) {
            return this.insertReturnPK(wallet);
        }
        this.update(wallet);
        return wallet.getPK();
    }
}