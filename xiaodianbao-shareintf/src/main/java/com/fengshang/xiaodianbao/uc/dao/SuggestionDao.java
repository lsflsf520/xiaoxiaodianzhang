package com.fengshang.xiaodianbao.uc.dao;

import com.fengshang.xiaodianbao.uc.entity.Suggestion;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionDao extends IBaseDao<Integer, Suggestion> {
    Integer insertReturnPK(Suggestion suggestion);
}