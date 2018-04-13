package com.fengshang.xiaodianbao.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.fengshang.xiaodianbao.shop.service.ShopService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("shop")
public class ShopController {

	@Resource
	private ShopService shopService;

	@RequestMapping("loadMyShops")
	@ResponseBody
	public ResultModel loadMyShops() {
		List<Shop> dbDatas = shopService.loadMyShops(ThreadUtil.getUidInt());

		return new ResultModel(dbDatas);
	}

	@RequestMapping("doSave")
	@ResponseBody
	public ResultModel doSave(Shop formData) {
		shopService.doSave(formData);

		return new ResultModel(true);
	}

	@RequestMapping("load/{shopId}")
	@ResponseBody
	public ResultModel loadById(@PathVariable("shopId") int shopId) {
		Shop dbData = shopService.findById(shopId);

		return new ResultModel(dbData);
	}

	@RequestMapping("loadMyShopState")
	@ResponseBody
	public ResultModel loadMyShopState() {
		Shop dbData = shopService.loadMyFirstShop(ThreadUtil.getUidInt());

		String state = "NON";// 没有shop
		if (dbData != null) {
			state = dbData.getCheckState().toString();
		}
		return ResultModel.buildMapResultModel().put("shopState", state).put("shop", dbData);
	}

}
