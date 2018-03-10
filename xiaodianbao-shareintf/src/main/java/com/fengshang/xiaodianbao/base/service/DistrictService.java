package com.fengshang.xiaodianbao.base.service;

import com.fengshang.xiaodianbao.base.dao.DistrictDao;
import com.fengshang.xiaodianbao.base.entity.District;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DistrictService extends AbstractBaseService<Integer, District> {
    @Resource
    private DistrictDao districtDao;

    @Override
    protected IBaseDao<Integer, District> getBaseDao() {
        return districtDao;
    }

    public Integer insertReturnPK(District district) {
        districtDao.insertReturnPK(district);
        return district.getPK();
    }

    public Integer doSave(District district) {
        if (district.getPK() == null) {
            return this.insertReturnPK(district);
        }
        this.update(district);
        return district.getPK();
    }
}