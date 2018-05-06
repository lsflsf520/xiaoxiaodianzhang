package com.fengshang.xiaodianbao.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.fengshang.xiaodianbao.shop.service.CouponTmplService;
import com.fengshang.xiaodianbao.shop.service.ShopCouponService;
import com.xyz.tools.common.bean.ResultModel;

@Controller
@RequestMapping("shop/coupon")
public class ShopCouponController {

	@Resource
	private CouponTmplService couponTmplService;

	@Resource
	private ShopCouponService shopCouponService;

	@RequestMapping("loadTmpls")
	@ResponseBody
	public ResultModel loadTmpls(CouponType couponType) {
		List<CouponTmpl> couponTmpls = couponTmplService.loadTmplByType(couponType);

		return ResultModel.buildMapResultModel().put("couponTmpls", couponTmpls).put("couponType", couponType)
				.put("descp", couponType.getDescp());
	}

	@RequestMapping("loadTmpl")
	@ResponseBody
	public ResultModel loadTmplById(int tmplId) {
		CouponTmpl dbData = couponTmplService.findById(tmplId);

		return new ResultModel(dbData);
	}

	@RequestMapping("loadDefTmpl")
	@ResponseBody
	public ResultModel loadDefTmpl(CouponType couponType) {
		CouponTmpl tmpl = couponTmplService.loadDefTmplByType(couponType);

		return new ResultModel(tmpl);
	}

	@RequestMapping("doSave")
	@ResponseBody
	public ResultModel saveShopCoupon(ShopCoupon formData) {
		if (formData.getDiscountType() == null || formData.getDiscount() == null
				|| StringUtils.isBlank(formData.getTmplFileName())) {
			return new ResultModel("ILLEGAL_PARAM", "参数错误！");
		}
		shopCouponService.doSave(formData);

		return new ResultModel(true);
	}

	@RequestMapping("loadMyCoupons")
	@ResponseBody
	public ResultModel loadMyCoupons() {
		List<ShopCoupon> diyongCoupons = shopCouponService.loadCouponByType(CouponType.Diyong);
		List<ShopCoupon> cashCoupons = shopCouponService.loadCouponByType(CouponType.Cash);

		return ResultModel.buildMapResultModel().put("diyongCoupons", diyongCoupons).put("cashCoupons", cashCoupons);
	}

	@RequestMapping("loadShopCoupon")
	@ResponseBody
	public ResultModel loadShopCoupon(int shopCouponId) {
		ShopCoupon dbData = shopCouponService.findById(shopCouponId);

		return new ResultModel(dbData);
	}

	/*
	 * @RequestMapping("loadCouponData")
	 * 
	 * @ResponseBody public ResultModel loadCouponData(Integer shopCouponId, Integer
	 * tmplId) { ShopCoupon shopCoupon = null; if (shopCouponId != null) {
	 * shopCoupon = shopCouponService.findById(shopCouponId); } CouponTmpl
	 * couponTmpl = null; if (tmplId == null) { couponTmpl =
	 * couponTmplService.findById(tmplId); }
	 * 
	 * return ResultModel.buildMapResultModel().put("shopCoupon",
	 * shopCoupon).put("couponTmpl", couponTmpl); }
	 */
}
