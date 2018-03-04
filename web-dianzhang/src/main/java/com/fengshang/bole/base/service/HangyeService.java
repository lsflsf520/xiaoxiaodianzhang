package com.fengshang.bole.base.service;

import com.fengshang.bole.base.dao.HangyeDao;
import com.fengshang.bole.base.entity.Hangye;
import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class HangyeService extends AbstractBaseService<Integer, Hangye> {
    @Resource
    private HangyeDao hangyeDao;

    @Override
    protected IBaseDao<Integer, Hangye> getBaseDao() {
        return hangyeDao;
    }

    public Integer insertReturnPK(Hangye hangye) {
        hangyeDao.insertReturnPK(hangye);
        return hangye.getPK();
    }

    public Integer doSave(Hangye hangye) {
        if (hangye.getPK() == null) {
            return this.insertReturnPK(hangye);
        }
        this.update(hangye);
        return hangye.getPK();
    }
}