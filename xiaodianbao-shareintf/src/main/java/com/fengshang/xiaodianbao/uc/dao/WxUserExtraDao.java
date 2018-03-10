package com.fengshang.xiaodianbao.uc.dao;

import com.fengshang.xiaodianbao.uc.entity.WxUserExtra;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserExtraDao extends IBaseDao<Integer, WxUserExtra> {
    Integer insertReturnPK(WxUserExtra wxUserExtra);
}