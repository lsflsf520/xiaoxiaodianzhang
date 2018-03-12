package com.fengshang.xiaodianbao.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.fengshang.xiaodianbao.uc.service.WxUserService;
import com.xyz.tools.cache.constant.DefaultJedisKeyNS;
import com.xyz.tools.cache.redis.ShardJedisTool;
import com.xyz.tools.common.exception.BaseRuntimeException;
import com.xyz.tools.common.utils.JsonUtil;
import com.xyz.tools.common.utils.ThreadUtil;
import com.xyz.tools.web.aop.AbstractInterceptor;

public class WxSessionInterceptor extends AbstractInterceptor{
	
	@Resource
	private WxUserService wxUserService;

	@Override
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			String requestUri) throws Exception {
		String openId = ThreadUtil.getToken();
		if(StringUtils.isBlank(openId)) {
			throw new BaseRuntimeException("ILLEGAL_REQUEST", "非法的请求", "openId is null");
		}
		WxUser dbData = null;
		String cacheData = ShardJedisTool.hget(DefaultJedisKeyNS.session, openId, "userinfo");
		if(StringUtils.isBlank(cacheData)) {
			dbData = wxUserService.loadByOpenId(openId);
			if(dbData == null) {
				throw new BaseRuntimeException("WX_USER_NOT_EXIST", "没找到当前openId("+openId+")对应的账号");
			}
			
			ShardJedisTool.hset(DefaultJedisKeyNS.session, openId, "userinfo", JsonUtil.create().toJson(dbData));
		} else {
			dbData = JsonUtil.create().fromJson(cacheData, WxUser.class);
		}
		
		ThreadUtil.setCurrUser(dbData);
		
		return true;
	}

}
