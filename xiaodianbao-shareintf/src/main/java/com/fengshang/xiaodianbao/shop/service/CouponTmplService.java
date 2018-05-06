package com.fengshang.xiaodianbao.shop.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.shop.dao.CouponTmplDao;
import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class CouponTmplService extends AbstractBaseService<Integer, CouponTmpl> {
	@Resource
	private CouponTmplDao couponTmplDao;

	@Override
	protected IBaseDao<Integer, CouponTmpl> getBaseDao() {
		return couponTmplDao;
	}

	public Integer insertReturnPK(CouponTmpl couponTmpl) {
		couponTmplDao.insertReturnPK(couponTmpl);
		return couponTmpl.getPK();
	}

	public Integer doSave(CouponTmpl couponTmpl) {
		couponTmpl.setLastUptime(new Date());
		if (couponTmpl.getPK() == null) {
			couponTmpl.setStatus(CommonStatus.NORMAL);
			couponTmpl.setCreateTime(couponTmpl.getLastUptime());
			return this.insertReturnPK(couponTmpl);
		}
		this.update(couponTmpl);
		return couponTmpl.getPK();
	}

	public List<CouponTmpl> loadTmplByType(CouponType couponType) {
		CouponTmpl query = new CouponTmpl();
		query.setCouponType(couponType);
		query.setStatus(CommonStatus.NORMAL);

		return this.findByEntity(query, "last_uptime.desc");
	}

	public CouponTmpl loadDefTmplByType(CouponType couponType) {
		CouponTmpl query = new CouponTmpl();
		query.setCouponType(couponType);
		query.setStatus(CommonStatus.NORMAL);

		return this.findOne(query, "last_uptime", Direction.DESC);
	}
}