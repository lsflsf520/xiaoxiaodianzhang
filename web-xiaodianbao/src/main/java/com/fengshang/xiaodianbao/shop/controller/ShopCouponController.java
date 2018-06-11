package com.fengshang.xiaodianbao.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.DiscountType;
import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.shop.entity.ShopCoupon;
import com.fengshang.xiaodianbao.shop.service.CouponTmplService;
import com.fengshang.xiaodianbao.shop.service.ShopCouponService;
import com.fengshang.xiaodianbao.shop.service.ShopService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("shop/coupon")
public class ShopCouponController {

	@Resource
	private CouponTmplService couponTmplService;
	@Resource
	private ShopCouponService shopCouponService;
	@Resource
	private ShopService shopService;

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
		formData.setWxUid(ThreadUtil.getUidInt());
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

	@RequestMapping("loadByShopId")
	@ResponseBody
	public ResultModel loadByShopId(int shopId) {
		List<ShopCoupon> dbDatas = shopCouponService.loadByShopId(shopId);
		Shop shop = shopService.findById(shopId);

		return ResultModel.buildMapResultModel().put("shop", buildShop(shop)).put("dbDatas", buildAwardData(dbDatas));
	}

	private Map<String, Object> buildShop(Shop shop) {
		Map<String, Object> valMap = new HashMap<>();
		valMap.put("id", shop.getId());
		valMap.put("name", shop.getAlias());
		valMap.put("logo", shop.getLogo());
		valMap.put("addr", shop.getDetailAddr());

		return valMap;
	}

	private List<Map<String, Object>> buildAwardData(List<ShopCoupon> dbDatas) {
		List<Map<String, Object>> coupons = new ArrayList<Map<String, Object>>();
		if (!CollectionUtils.isEmpty(dbDatas)) {
			for (ShopCoupon coupon : dbDatas) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", coupon.getId());
				map.put("discount", coupon.getDiscount());
				map.put("couponType", coupon.getCouponType());
				map.put("minAmount", coupon.getMinAmount());
				map.put("unit", DiscountType.Amount.equals(coupon.getDiscountType()) ? "元" : "折");

				coupons.add(map);
			}
		}

		return coupons;
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
