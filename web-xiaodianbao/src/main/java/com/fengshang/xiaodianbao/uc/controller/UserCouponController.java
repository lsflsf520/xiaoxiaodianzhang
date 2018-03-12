package com.fengshang.xiaodianbao.uc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponState;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.wallet.entity.UserCoupon;
import com.fengshang.xiaodianbao.wallet.service.UserCouponService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("uc/coupon")
public class UserCouponController {
	
	@Resource
	private UserCouponService userCouponService;
	
	@RequestMapping("load/{couponState}")
	@ResponseBody
	public ResultModel loadMyCoupons(@PathVariable("couponState") CouponState couponState, int currPage) {
		int wxUid = ThreadUtil.getUidInt();
		
		Map<Shop, List<UserCoupon>> dbDataMap = userCouponService.loadMyCouponsGroupbyShop(wxUid, couponState, currPage);
		
		return new ResultModel(dbDataMap);
	}

}
