package com.fengshang.bole.base.dao;

import com.fengshang.bole.base.entity.Job;
import com.ujigu.secure.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDao extends IBaseDao<Integer, Job> {
    Integer insertReturnPK(Job job);
}