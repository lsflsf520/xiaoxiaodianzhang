package com.fengshang.xiaodianbao.base.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.base.entity.District;
import com.fengshang.xiaodianbao.base.service.DistrictService;
import com.xyz.tools.common.bean.ResultModel;

@Controller
@RequestMapping("base/district")
public class DistrictController {
	
	@Resource
	private DistrictService districtService;

	@RequestMapping("loadByParentId")
	@ResponseBody
	private ResultModel loadByParentId(int parentId) {
		List<District> datas = districtService.loadByParentId(parentId);
		
		return new ResultModel(datas);
	}
	
}
