package com.fengshang.xiaodianbao.uc.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshang.xiaodianbao.base.entity.District;
import com.fengshang.xiaodianbao.base.service.DistrictService;
import com.fengshang.xiaodianbao.shop.service.ShopService;
import com.fengshang.xiaodianbao.uc.entity.WxUser;
import com.fengshang.xiaodianbao.uc.entity.WxUserExtra;
import com.fengshang.xiaodianbao.uc.service.WxUserExtraService;
import com.fengshang.xiaodianbao.uc.service.WxUserService;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.common.constant.CheckState;
import com.xyz.tools.common.constant.Sex;
import com.xyz.tools.common.utils.LogUtils;
import com.xyz.tools.common.utils.StringUtil;
import com.xyz.tools.common.utils.ThreadUtil;
import com.xyz.tools.web.util.UserLoginHelper;
import com.xyz.tools.web.util.WxTool;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

@Controller
@RequestMapping("uc/wxuser")
public class WxUserController {

	// private final static String WX_SESSION_KEY = "sessionKey";

	@Resource
	private WxUserService wxUserService;
	@Resource
	private WxUserExtraService wxUserExtraService;
	@Resource
	private DistrictService districtService;
	@Resource
	private UserLoginHelper userLoginHelper;
	@Resource
	private ShopService shopService;

	@RequestMapping("dologon")
	@ResponseBody
	public ResultModel doLogon(HttpServletResponse response, String code) {
		String token = "";
		try {
			WxMaJscode2SessionResult sessionInfo = WxTool.getWxMaUserService().getSessionInfo(code);
			String openId = sessionInfo.getOpenid();
			String unionId = sessionInfo.getUnionid();
			WxUser dbData = wxUserService.loadByOpenId(openId);
			if (dbData == null) {
				dbData = new WxUser();
				dbData.setOpenId(openId);
				dbData.setUnionId(unionId);
				wxUserService.doSave(dbData);
			}

			token = userLoginHelper.storeSession(dbData, response, false);
			userLoginHelper.setWxSessionKey(sessionInfo.getSessionKey());
		} catch (WxErrorException e) {
			LogUtils.error("dologin|INVALID_CODE", e);
		}

		return StringUtils.isBlank(token) ? new ResultModel("LOGIN_ERR", "登录失败")
				: ResultModel.buildMapResultModel().put(ThreadUtil.TOKEN_KEY, token);
	}

	@RequestMapping("saveUserInfo")
	@ResponseBody
	public ResultModel saveUserInfo(String encryptedData, String iv) {
		WxUser wxuser = ThreadUtil.getCurrUser();
		String wxSessionKey = userLoginHelper.getWxSessionKey();
		WxMaUserInfo userInfo = WxTool.getWxMaUserService().getUserInfo(wxSessionKey, encryptedData, iv);

		wxuser.setNickName(userInfo.getNickName());
		wxuser.setHeadImg(userInfo.getAvatarUrl());
		wxuser.setLastUptime(new Date());
		wxuser.setSex("1".equals(userInfo.getGender()) ? Sex.M : "2".equals(userInfo.getGender()) ? Sex.F : Sex.U);
		String unionId = wxuser.getUnionId();
		if (!StringUtils.isBlank(userInfo.getUnionId())) {
			unionId = userInfo.getUnionId();
		}
		wxuser.setUnionId(unionId);
		wxUserService.doSave(wxuser);

		wxuser.setOpenId(null);
		wxuser.setUnionId(null);
		if (StringUtils.isNotBlank(wxuser.getPhone())) {
			wxuser.setPhone(StringUtil.stringHide(wxuser.getPhone()));
		}

		return new ResultModel(wxuser);
	}

	@RequestMapping("checkSession")
	@ResponseBody
	public ResultModel checkSession() {
		return new ResultModel(true);
	}

	@RequestMapping("loadMyInfo")
	@ResponseBody
	public ResultModel loadMyInfo() {
		WxUser wxuser = ThreadUtil.getCurrUser();
		WxUserExtra userExtra = wxUserExtraService.findById(wxuser.getId());
		List<District> provinces = districtService.loadByParentId(0);
		CheckState shopState = shopService.loadMyFirstShopState(wxuser.getId());

		return ResultModel.buildMapResultModel().put("extra", userExtra).put("provinces", provinces).put("shopState",
				shopState);
	}

	@RequestMapping("msgSwitch")
	@ResponseBody
	public ResultModel msgSwitch(String msgType, Bool open) {
		boolean result = wxUserExtraService.updateMsgSwitch(ThreadUtil.getUidInt(), msgType, open);
		return new ResultModel(result);
	}
}
