package com.fengshang.xiaodianbao.uc.service;

import com.fengshang.xiaodianbao.uc.dao.SuggestionDao;
import com.fengshang.xiaodianbao.uc.entity.Suggestion;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService extends AbstractBaseService<Integer, Suggestion> {
    @Resource
    private SuggestionDao suggestionDao;

    @Override
    protected IBaseDao<Integer, Suggestion> getBaseDao() {
        return suggestionDao;
    }

    public Integer insertReturnPK(Suggestion suggestion) {
        suggestionDao.insertReturnPK(suggestion);
        return suggestion.getPK();
    }

    public Integer doSave(Suggestion suggestion) {
        if (suggestion.getPK() == null) {
            return this.insertReturnPK(suggestion);
        }
        this.update(suggestion);
        return suggestion.getPK();
    }
}