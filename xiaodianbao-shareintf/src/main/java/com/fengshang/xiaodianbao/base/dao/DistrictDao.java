package com.fengshang.xiaodianbao.base.dao;

import com.fengshang.xiaodianbao.base.entity.District;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends IBaseDao<Integer, District> {
    Integer insertReturnPK(District district);
}