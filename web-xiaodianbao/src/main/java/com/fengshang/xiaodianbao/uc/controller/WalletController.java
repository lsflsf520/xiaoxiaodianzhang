package com.fengshang.xiaodianbao.uc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.shop.service.ShopService;
import com.fengshang.xiaodianbao.uc.entity.UserCouponNum;
import com.fengshang.xiaodianbao.uc.service.UserCouponNumService;
import com.fengshang.xiaodianbao.wallet.entity.Wallet;
import com.fengshang.xiaodianbao.wallet.service.WalletService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("uc/wallet")
public class WalletController {

	@Resource
	private WalletService walletService;
	@Resource
	private ShopService shopService;
	@Resource
	private UserCouponNumService userCouponNumService;

	@RequestMapping("infoview")
	@ResponseBody
	public ResultModel infoview() {
		Wallet wallet = walletService.findById(ThreadUtil.getUidInt());

		UserCouponNum discountCouponNum = userCouponNumService.loadByUidAndCouponType(ThreadUtil.getUidInt(),
				CouponType.Discount);
		UserCouponNum cashCouponNum = userCouponNumService.loadByUidAndCouponType(ThreadUtil.getUidInt(),
				CouponType.Cash);

		List<Shop> myShops = shopService.loadMyShops(ThreadUtil.getUidInt());

		return ResultModel.buildMapResultModel()
				.put("balance", wallet == null || wallet.getBalance() == null ? 0 : wallet.getBalance())
				.put("discountCouponNum", discountCouponNum).put("cashCouponNum", cashCouponNum)
				.put("myShops", myShops);
	}

}
