package com.fengshang.xiaodianbao.shop.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.shop.dao.ShopCouponDao;
import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.common.exception.BaseRuntimeException;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ShopCouponService extends AbstractBaseService<Integer, ShopCoupon> {
	@Resource
	private ShopCouponDao shopCouponDao;

	@Override
	protected IBaseDao<Integer, ShopCoupon> getBaseDao() {
		return shopCouponDao;
	}

	public Integer insertReturnPK(ShopCoupon shopCoupon) {
		shopCouponDao.insertReturnPK(shopCoupon);
		return shopCoupon.getPK();
	}

	public Integer doSave(ShopCoupon shopCoupon) {
		shopCoupon.setLastUptime(new Date());
		if (shopCoupon.getPK() == null) {
			boolean exist = existSameCoupon(shopCoupon.getShopId(), shopCoupon.getCouponType(),
					shopCoupon.getDiscount());
			if (exist) {
				throw new BaseRuntimeException("ALREADY_EXIST", "同一种类型的券下不能存在相同面额的两张及以上的券");
			}

			shopCoupon.setStatus(CommonStatus.NORMAL);
			shopCoupon.setCreateTime(shopCoupon.getLastUptime());
			return this.insertReturnPK(shopCoupon);
		}
		this.update(shopCoupon);
		return shopCoupon.getPK();
	}

	private boolean existSameCoupon(int shopId, CouponType couponType, Double discount) {
		ShopCoupon query = new ShopCoupon();
		query.setShopId(shopId);
		query.setCouponType(couponType);
		query.setDiscount(discount);

		return this.findOne(query) != null;
	}

	public List<ShopCoupon> loadCouponByType(CouponType couponType) {
		ShopCoupon query = new ShopCoupon();
		query.setCouponType(couponType);

		return this.findByEntity(query, "last_uptime.desc");
	}

	public List<ShopCoupon> loadByShopId(int shopId) {
		ShopCoupon query = new ShopCoupon();
		query.setShopId(shopId);
		query.setStatus(CommonStatus.NORMAL);
		query.setOrdseg("last_uptime.desc");

		return this.findTopItems(query, 6);
	}

}