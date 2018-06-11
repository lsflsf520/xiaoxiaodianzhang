package com.fengshang.xiaodianbao.uc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.uc.dao.WxUserExtraDao;
import com.fengshang.xiaodianbao.uc.entity.WxUserExtra;
import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

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

	public boolean updateMsgSwitch(int wxUid, String msgType, Bool open) {
		WxUserExtra extra = new WxUserExtra();
		extra.setWxUid(wxUid);
		if ("servMsg".equals(msgType)) {
			extra.setServMsg(open);
		} else if ("smsMsg".equals(msgType)) {
			extra.setSmsMsg(open);
		}
		return wxUserExtraDao.updateByPK(extra) >= 0;
	}
}