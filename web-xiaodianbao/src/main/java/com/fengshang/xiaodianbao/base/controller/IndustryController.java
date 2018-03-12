package com.fengshang.xiaodianbao.base.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.base.entity.Industry;
import com.fengshang.xiaodianbao.base.service.IndustryService;
import com.xyz.tools.common.bean.ResultModel;

@Controller
@RequestMapping("base/industry")
public class IndustryController {
	
	@Resource
	private IndustryService industryService;
	
	@RequestMapping("loadByParentId")
	@ResponseBody
	public ResultModel loadByParentId(int parentId) {
		List<Industry> datas = industryService.loadByParentId(parentId);
		
		return new ResultModel(datas);
	}

}
