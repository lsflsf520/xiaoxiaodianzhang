package com.fengshang.xiaodianbao.uc.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.fengshang.xiaodianbao.uc.service.WxUserService;
import com.xyz.tools.common.bean.ResultModel;

@Controller
@RequestMapping("wxuser")
public class WxUserController {
	
	@Resource
	private WxUserService wxUserService;

	@RequestMapping("doSave")
	@ResponseBody
	public ResultModel doSave(WxUser formData) {
		if(StringUtils.isBlank(formData.getOpenId())) {
			return new ResultModel("ILLEGAL_PARAM", "参数错误，请重试！");
		}
		wxUserService.doSave(formData);
		
		return new ResultModel(true);
	}
	
}
