package com.fengshang.xiaodianbao.uc.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.uc.dao.UserCouponNumDao;
import com.fengshang.xiaodianbao.uc.entity.UserCouponNum;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class UserCouponNumService extends AbstractBaseService<Integer, UserCouponNum> {
	@Resource
	private UserCouponNumDao userCouponNumDao;

	@Override
	protected IBaseDao<Integer, UserCouponNum> getBaseDao() {
		return userCouponNumDao;
	}

	public Integer insertReturnPK(UserCouponNum userCouponNum) {
		userCouponNumDao.insertReturnPK(userCouponNum);
		return userCouponNum.getPK();
	}

	public Integer doSave(UserCouponNum userCouponNum) {
		userCouponNum.setLastUptime(new Date());
		if (userCouponNum.getPK() == null) {
			userCouponNum.setCouponType(CouponType.Diyong);
			userCouponNum.setCreateTime(userCouponNum.getLastUptime());
			return this.insertReturnPK(userCouponNum);
		}
		this.update(userCouponNum);
		return userCouponNum.getPK();
	}

	public UserCouponNum loadByUidAndCouponType(int wxUid, CouponType couponType) {
		UserCouponNum query = new UserCouponNum();
		query.setCouponType(couponType);

		return this.findOne(query);
	}
}