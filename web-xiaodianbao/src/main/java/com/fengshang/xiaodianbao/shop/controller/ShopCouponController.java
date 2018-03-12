package com.fengshang.xiaodianbao.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.fengshang.xiaodianbao.shop.service.CouponTmplService;
import com.xyz.tools.common.bean.ResultModel;

@Controller
@RequestMapping("coupon")
public class ShopCouponController {
	
	@Resource
	private CouponTmplService couponTmplService;
	
	@RequestMapping("loadTmpls/{couponType}")
	public ResultModel loadTmpls(@PathVariable("couponType") CouponType couponType) {
		List<CouponTmpl> dbDatas = couponTmplService.loadTmplByType(couponType);
		
		return new ResultModel(dbDatas);
	}
	
	@RequestMapping("loadTmpl/{tmplId}")
	@ResponseBody
	public ResultModel loadTmplById(@PathVariable("tmplId") int tmplId) {
		CouponTmpl dbData = couponTmplService.findById(tmplId);
		
		return new ResultModel(dbData);
	}

}
