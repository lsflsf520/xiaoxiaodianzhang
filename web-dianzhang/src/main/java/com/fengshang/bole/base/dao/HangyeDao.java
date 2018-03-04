package com.fengshang.bole.base.dao;

import com.fengshang.bole.base.entity.Hangye;
import com.ujigu.secure.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface HangyeDao extends IBaseDao<Integer, Hangye> {
    Integer insertReturnPK(Hangye hangye);
}