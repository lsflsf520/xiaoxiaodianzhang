package com.fengshang.xiaodianbao.uc.service;

import com.fengshang.xiaodianbao.uc.dao.WxUserExtraDao;
import com.fengshang.xiaodianbao.uc.entity.WxUserExtra;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WxUserExtraService extends AbstractBaseService<Integer, WxUserExtra> {
    @Resource
    private WxUserExtraDao wxUserExtraDao;

    @Override
    protected IBaseDao<Integer, WxUserExtra> getBaseDao() {
        return wxUserExtraDao;
    }

    public Integer insertReturnPK(WxUserExtra wxUserExtra) {
        wxUserExtraDao.insertReturnPK(wxUserExtra);
        return wxUserExtra.getPK();
    }

    public Integer doSave(WxUserExtra wxUserExtra) {
        if (wxUserExtra.getPK() == null) {
            return this.insertReturnPK(wxUserExtra);
        }
        this.update(wxUserExtra);
        return wxUserExtra.getPK();
    }
}