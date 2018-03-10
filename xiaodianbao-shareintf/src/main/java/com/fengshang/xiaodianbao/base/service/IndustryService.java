package com.fengshang.xiaodianbao.base.service;

import com.fengshang.xiaodianbao.base.dao.IndustryDao;
import com.fengshang.xiaodianbao.base.entity.Industry;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IndustryService extends AbstractBaseService<Integer, Industry> {
    @Resource
    private IndustryDao industryDao;

    @Override
    protected IBaseDao<Integer, Industry> getBaseDao() {
        return industryDao;
    }

    public Integer insertReturnPK(Industry industry) {
        industryDao.insertReturnPK(industry);
        return industry.getPK();
    }

    public Integer doSave(Industry industry) {
        if (industry.getPK() == null) {
            return this.insertReturnPK(industry);
        }
        this.update(industry);
        return industry.getPK();
    }
}