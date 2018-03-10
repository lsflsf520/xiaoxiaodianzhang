package com.fengshang.xiaodianbao.base.dao;

import com.fengshang.xiaodianbao.base.entity.Industry;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryDao extends IBaseDao<Integer, Industry> {
    Integer insertReturnPK(Industry industry);
}