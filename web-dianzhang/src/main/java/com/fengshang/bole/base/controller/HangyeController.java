package com.fengshang.bole.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ujigu.secure.common.bean.ResultModel;

@Controller
@RequestMapping("base/hangye")
public class HangyeController {
	
	@RequestMapping("receive")
	public ResultModel receive(String json) {
		System.out.println(json);
		
		return new ResultModel(true);
	}

}
