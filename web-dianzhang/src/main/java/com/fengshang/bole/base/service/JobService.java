package com.fengshang.bole.base.service;

import com.fengshang.bole.base.dao.JobDao;
import com.fengshang.bole.base.entity.Job;
import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class JobService extends AbstractBaseService<Integer, Job> {
    @Resource
    private JobDao jobDao;

    @Override
    protected IBaseDao<Integer, Job> getBaseDao() {
        return jobDao;
    }

    public Integer insertReturnPK(Job job) {
        jobDao.insertReturnPK(job);
        return job.getPK();
    }

    public Integer doSave(Job job) {
        if (job.getPK() == null) {
            return this.insertReturnPK(job);
        }
        this.update(job);
        return job.getPK();
    }
}