package com.fengshang.xiaodianbao.wallet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fengshang.xiaodianbao.constant.CouponState;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.shop.service.ShopService;
import com.fengshang.xiaodianbao.wallet.dao.UserCouponDao;
import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;
import com.fengshang.xiaodianbao.wallet.vo.UserCouponVO;
import com.xyz.tools.db.bean.PageData;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class UserCouponService extends AbstractBaseService<Integer, UserCoupon> {
	@Resource
	private UserCouponDao userCouponDao;

	@Resource
	private ShopService shopService;

	@Override
	protected IBaseDao<Integer, UserCoupon> getBaseDao() {
		return userCouponDao;
	}

	public Integer insertReturnPK(UserCoupon userCoupon) {
		userCouponDao.insertReturnPK(userCoupon);
		return userCoupon.getPK();
	}

	public Integer doSave(UserCoupon userCoupon) {
		if (userCoupon.getPK() == null) {
			return this.insertReturnPK(userCoupon);
		}
		this.update(userCoupon);
		return userCoupon.getPK();
	}

	public List<UserCoupon> loadMyCoupons(int wxUid, CouponState couponState, int currPage) {
		UserCoupon query = new UserCoupon();
		query.setWxUid(wxUid);
		query.setCouponState(couponState);
		query.setPage(currPage);
		query.setLimit(10);

		PageData<UserCoupon> dataPage = this.findByPage(query);

		return dataPage.getDatas();
	}

	public List<UserCouponVO> loadMyCouponsGroupbyShop(int wxUid, CouponState couponState, int currPage) {
		List<UserCouponVO> couponVOs = new ArrayList<>();
		List<UserCoupon> dbDatas = loadMyCoupons(wxUid, couponState, currPage);
		if (CollectionUtils.isEmpty(dbDatas)) {
			return couponVOs;
		}

		List<Integer> shopIds = new ArrayList<>();
		for (UserCoupon dbData : dbDatas) {
			shopIds.add(dbData.getShopId());
		}

		Map<Shop, List<UserCoupon>> shop2CouponMap = new HashMap<>();
		Map<Integer, Shop> shopMap = shopService.loadShops(shopIds.toArray(new Integer[0]));
		for (UserCoupon dbData : dbDatas) {
			Shop shop = shopMap.get(dbData.getShopId());

			List<UserCoupon> coupons = shop2CouponMap.get(shop);
			if (coupons == null) {
				coupons = new ArrayList<>();
				shop2CouponMap.put(shop, coupons);
			}

			coupons.add(dbData);
		}
		for (Entry<Shop, List<UserCoupon>> entry : shop2CouponMap.entrySet()) {
			UserCouponVO couponVO = new UserCouponVO();
			couponVO.setShop(entry.getKey());
			couponVO.setCoupons(entry.getValue());

			couponVOs.add(couponVO);
		}

		return couponVOs;
	}
}