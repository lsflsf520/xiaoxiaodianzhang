package com.fengshang.bole.base.dao;

import com.fengshang.bole.base.entity.JobClassify;
import com.ujigu.secure.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface JobClassifyDao extends IBaseDao<Integer, JobClassify> {
    Integer insertReturnPK(JobClassify jobClassify);
}