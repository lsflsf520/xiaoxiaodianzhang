package com.fengshang.bole.base.service;

import com.fengshang.bole.base.dao.JobClassifyDao;
import com.fengshang.bole.base.entity.JobClassify;
import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class JobClassifyService extends AbstractBaseService<Integer, JobClassify> {
    @Resource
    private JobClassifyDao jobClassifyDao;

    @Override
    protected IBaseDao<Integer, JobClassify> getBaseDao() {
        return jobClassifyDao;
    }

    public Integer insertReturnPK(JobClassify jobClassify) {
        jobClassifyDao.insertReturnPK(jobClassify);
        return jobClassify.getPK();
    }

    public Integer doSave(JobClassify jobClassify) {
        if (jobClassify.getPK() == null) {
            return this.insertReturnPK(jobClassify);
        }
        this.update(jobClassify);
        return jobClassify.getPK();
    }
}