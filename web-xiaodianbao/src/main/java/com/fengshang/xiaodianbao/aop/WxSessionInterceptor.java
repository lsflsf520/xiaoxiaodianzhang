package com.fengshang.xiaodianbao.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.fengshang.xiaodianbao.uc.service.WxUserService;
import com.xyz.tools.common.exception.BaseRuntimeException;
import com.xyz.tools.common.utils.ThreadUtil;
import com.xyz.tools.web.aop.AbstractInterceptor;
import com.xyz.tools.web.util.UserLoginHelper;

public class WxSessionInterceptor extends AbstractInterceptor {

	@Resource
	private WxUserService wxUserService;
	@Resource
	private UserLoginHelper userLoginHelper;

	@Override
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			String requestUri) throws Exception {
		WxUser suser = userLoginHelper.getSessionUser();
		if (suser == null) {
			throw new BaseRuntimeException("NOT_LOGON", "会话已过期，请重新登录！");
		}

		userLoginHelper.continueSessionTTL();
		ThreadUtil.setCurrUser(suser);

		return true;
	}

}
