package com.fengshang.xiaodianbao.uc.dao;

import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserDao extends IBaseDao<Integer, WxUser> {
    Integer insertReturnPK(WxUser wxUser);
}