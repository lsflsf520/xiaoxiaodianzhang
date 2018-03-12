package com.fengshang.xiaodianbao.uc.service;

import com.fengshang.xiaodianbao.uc.dao.WxUserDao;
import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.common.exception.BaseRuntimeException;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WxUserService extends AbstractBaseService<Integer, WxUser> {
    @Resource
    private WxUserDao wxUserDao;

    @Override
    protected IBaseDao<Integer, WxUser> getBaseDao() {
        return wxUserDao;
    }

    public Integer insertReturnPK(WxUser wxUser) {
        wxUserDao.insertReturnPK(wxUser);
        return wxUser.getPK();
    }

    public Integer doSave(WxUser wxUser) {
    	    wxUser.setLastUptime(new Date());
        if (wxUser.getPK() == null) {
        	    WxUser dbData = loadByOpenId(wxUser.getOpenId());
        	    if(dbData != null) {
        	    	    throw new BaseRuntimeException("ALREADY_EXIST", "当前账号已存在，请勿重复操作！", "openId:" + wxUser.getOpenId());
        	    }
          	wxUser.setStatus(CommonStatus.NORMAL);
        	    wxUser.setCreateTime(wxUser.getLastUptime());
            return this.insertReturnPK(wxUser);
        }
        this.update(wxUser);
        return wxUser.getPK();
    }
    
    public WxUser loadByOpenId(String openId) {
    	    WxUser query = new WxUser();
    	    query.setOpenId(openId);
    	    
    	    return this.findOne(query);
    }
}