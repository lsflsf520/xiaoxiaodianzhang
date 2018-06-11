package com.fengshang.xiaodianbao.data.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.XdConstant;
import com.fengshang.xiaodianbao.data.entity.DataDayShop;
import com.fengshang.xiaodianbao.data.entity.DataMonthShop;
import com.fengshang.xiaodianbao.data.service.DataDayShopService;
import com.fengshang.xiaodianbao.data.service.DataMonthShopService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.DateUtil;

@Controller
@RequestMapping("data")
public class DataController {

	@Resource
	private DataDayShopService dataDayShopService;
	@Resource
	private DataMonthShopService dataMonthShopService;

	@RequestMapping("loadLatestDayData")
	@ResponseBody
	public ResultModel loadLatestDayData(int shopId, CouponType couponType) {
		List<DataDayShop> dbDatas = dataDayShopService.loadLatestData(shopId, couponType);

		Date startDate = DateUtil.timeAddByDays(new Date(), -XdConstant.MAX_DATA_DAYS);
		Date endDate = DateUtil.timeAddByDays(new Date(), -1);
		List<String> dateList = DateUtil.genDays(startDate, endDate, true);
		Map<String, DataDayShop> date2DataDayMap = buildDate2DataDay(dbDatas);
		List<Integer> recvedDatas = new ArrayList<>();
		List<Integer> usedDatas = new ArrayList<>();
		List<Integer> newUserDatas = new ArrayList<>();
		List<Integer> oldUserDatas = new ArrayList<>();
		for (String datestr : dateList) {
			DataDayShop dbData = date2DataDayMap.get(datestr);

			recvedDatas.add(dbData == null || dbData.getRecvedNum() == null ? 0 : dbData.getRecvedNum());
			usedDatas.add(dbData == null || dbData.getUsedNum() == null ? 0 : dbData.getUsedNum());
			newUserDatas.add(dbData == null || dbData.getNewUserNum() == null ? 0 : dbData.getNewUserNum());
			oldUserDatas.add(dbData == null || dbData.getOldUserNum() == null ? 0 : dbData.getOldUserNum());
		}

		return ResultModel.buildMapResultModel().put("categories", dateList).put("recvedDatas", recvedDatas)
				.put("usedDatas", usedDatas).put("newUserDatas", newUserDatas).put("oldUserDatas", oldUserDatas);
	}

	@RequestMapping("loadLatestMonthData")
	@ResponseBody
	public ResultModel loadLatestMonthData(int shopId, CouponType couponType) {
		List<DataMonthShop> dbDatas = dataMonthShopService.loadLatestData(shopId, couponType);

		Date endDate = DateUtil.parseDate(DateUtil.getFirstDayOfMonth(new Date()));
		Date startDate = DateUtil.timeAddByMonth(endDate, -XdConstant.MAX_DATA_MONTHS);
		List<String> dateList = DateUtil.genMonths(startDate, endDate);
		Map<String, DataMonthShop> date2DataDayMap = buildDate2DataMonth(dbDatas);
		List<Integer> recvedDatas = new ArrayList<>();
		List<Integer> usedDatas = new ArrayList<>();
		List<Integer> newUserDatas = new ArrayList<>();
		List<Integer> oldUserDatas = new ArrayList<>();
		for (String datestr : dateList) {
			DataMonthShop dbData = date2DataDayMap.get(datestr);

			recvedDatas.add(dbData == null || dbData.getRecvedNum() == null ? 0 : dbData.getRecvedNum());
			usedDatas.add(dbData == null || dbData.getUsedNum() == null ? 0 : dbData.getUsedNum());
			newUserDatas.add(dbData == null || dbData.getNewUserNum() == null ? 0 : dbData.getNewUserNum());
			oldUserDatas.add(dbData == null || dbData.getOldUserNum() == null ? 0 : dbData.getOldUserNum());
		}

		return ResultModel.buildMapResultModel().put("categories", dateList).put("recvedDatas", recvedDatas)
				.put("usedDatas", usedDatas).put("newUserDatas", newUserDatas).put("oldUserDatas", oldUserDatas);
	}

	private Map<String, DataDayShop> buildDate2DataDay(List<DataDayShop> dbDatas) {
		Map<String, DataDayShop> date2DataDayMap = new HashMap<>();
		for (DataDayShop dbData : dbDatas) {
			date2DataDayMap.put(DateUtil.getDateStr(dbData.getDataDay()), dbData);
		}

		return date2DataDayMap;
	}

	private Map<String, DataMonthShop> buildDate2DataMonth(List<DataMonthShop> dbDatas) {
		Map<String, DataMonthShop> date2DataMonthMap = new HashMap<>();
		for (DataMonthShop dbData : dbDatas) {
			date2DataMonthMap.put(DateUtil.getDateStr(dbData.getDataDay()), dbData);
		}

		return date2DataMonthMap;
	}

}
