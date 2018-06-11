package com.fengshang.xiaodianbao.wallet.vo;

import java.util.List;

import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;

public class UserCouponVO {

	private Shop shop;
	private List<UserCoupon> coupons;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<UserCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<UserCoupon> coupons) {
		this.coupons = coupons;
	}

}
