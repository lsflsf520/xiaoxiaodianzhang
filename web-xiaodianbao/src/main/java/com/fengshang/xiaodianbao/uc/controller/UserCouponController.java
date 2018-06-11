package com.fengshang.xiaodianbao.uc.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponState;
import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.fengshang.xiaodianbao.shop.service.ShopCouponService;
import com.fengshang.xiaodianbao.wallet.entity.CustomOrder;
import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;
import com.fengshang.xiaodianbao.wallet.service.CustomOrderService;
import com.fengshang.xiaodianbao.wallet.service.UserCouponService;
import com.fengshang.xiaodianbao.wallet.vo.UserCouponVO;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.constant.OrdState;
import com.xyz.tools.common.constant.PayMethod;
import com.xyz.tools.common.utils.DateUtil;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("uc/coupon")
public class UserCouponController {

	@Resource
	private UserCouponService userCouponService;
	@Resource
	private ShopCouponService shopCouponService;
	@Resource
	private CustomOrderService customOrderService;

	@RequestMapping("load")
	@ResponseBody
	public ResultModel loadMyCoupons(CouponState state, Integer currPage) {
		int wxUid = ThreadUtil.getUidInt();

		List<UserCouponVO> couponVOs = userCouponService.loadMyCouponsGroupbyShop(wxUid, state,
				currPage == null ? 1 : currPage);

		return new ResultModel(couponVOs);
	}

	@RequestMapping("recvCoupon")
	@ResponseBody
	public ResultModel recvCoupon(int shopCouponId) {
		ShopCoupon shopCoupon = shopCouponService.findById(shopCouponId);

		UserCoupon updata = convertUserCoupon(shopCoupon);

		Integer pk = userCouponService.insertReturnPK(updata);

		return new ResultModel(pk);
	}

	@RequestMapping("useCoupon")
	@ResponseBody
	public ResultModel useCoupon(int userCouponId, BigDecimal canUseFee, BigDecimal ordFee) {
		UserCoupon userCoupon = userCouponService.findById(userCouponId);
		if (userCoupon == null || !ThreadUtil.getUidInt().equals(userCoupon.getWxUid())
				|| !userCoupon.canUse(canUseFee)) {
			return new ResultModel("ILLEGAL_STATE", "数据状态异常");
		}

		CustomOrder order = new CustomOrder();
		order.setUserCouponId(userCouponId);
		order.setWxUid(userCoupon.getWxUid());
		order.setOrdFee(ordFee == null ? canUseFee.doubleValue() : ordFee.doubleValue());
		order.setCanUseFee(canUseFee.doubleValue());
		order.setLeftPayFee(ordFee == null ? 0 : ordFee.subtract(canUseFee).doubleValue());
		order.setBalancePayFee(0d);
		order.setThirdPayFee(0d);
		order.setPayMethod(PayMethod.OFFLINE);
		order.setOrdState(OrdState.PAYED);
		order.setCreateTime(new Date());
		order.setLastUptime(new Date());
		Integer orderId = customOrderService.insertReturnPK(order);

		userCoupon.setCouponState(CouponState.USED);
		userCoupon.setLastUptime(new Date());
		userCouponService.doSave(userCoupon);

		return new ResultModel(orderId);
	}

	private UserCoupon convertUserCoupon(ShopCoupon shopCoupon) {
		UserCoupon updata = new UserCoupon();
		updata.setWxUid(ThreadUtil.getUidInt());
		updata.setCouponType(shopCoupon.getCouponType());
		updata.setDiscountType(shopCoupon.getDiscountType());
		updata.setDiscount(shopCoupon.getDiscount());
		updata.setMinAmount(shopCoupon.getMinAmount());
		updata.setOnlinePay(shopCoupon.getOnlinePay());
		updata.setUseInHoliday(shopCoupon.getUseInHoliday());
		updata.setCanUseTime(shopCoupon.getCanUseTime());
		updata.setTmplFileName(shopCoupon.getTmplFileName());
		updata.setShopId(shopCoupon.getShopId());
		updata.setEffectTime(DateUtil.timeAddByDays(new Date(), shopCoupon.getEffectAfterRecvDay()));
		updata.setUneffectTime(DateUtil.timeAddByDays(updata.getEffectTime(), shopCoupon.getEffectDay()));
		updata.setSrcCode("SHOP_SCAN");
		updata.setCouponState(CouponState.RECVD);
		updata.setCreateTime(new Date());
		updata.setLastUptime(new Date());

		return updata;
	}

}
