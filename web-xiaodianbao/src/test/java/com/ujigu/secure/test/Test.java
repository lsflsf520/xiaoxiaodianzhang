package com.ujigu.secure.test;

import com.xyz.tools.common.utils.JsonUtil;
import com.xyz.tools.web.util.WxTool;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;

public class Test {

	public static void main(String[] args) {
		WxMaUserInfo userInfo = WxTool.getWxMaUserService().getUserInfo("NRHz4LCcsuXSp9qYCPnf8Q==",
				"ZATJ9UDCNV0cxYPiEUZE8xYjpF/FzY0plkL9Pe1ekQMbD4dx3VT19/o/csNTMl3J63NPN62B4fSru9j3bxl/KwTmu3FLmGA1BLMMLWdaoI9Bus2AVkHubo2YruPV2vDzMrZuM6Un3oKjdh1DO+YsJHcUFChXv3k0Bfgbtqxpj83481iMYNwDiG2ycjJK//KpuxH5MbfJmTisyoe3HsrI5plrc4XT+5xxC7v5p5nWceNXWJAx2tUJ148AtIiwcxY3Zl37ANuqXGVMLmIEq5huN89q4vc7sV4v4oyikUIW8wAxKFz31oet3rnA7p4aCeZR7vi3h8noqiCju9fwq7yiHUslmJX1n0/rIihEqGUYN+aDufJ5nAUht3uDoR3oiYi/ZcGhPgPD7TLJqt2V0UBx43mUlO9R4bqbVUtPyKWLeUPF4Vb8Jpw7pK3sfAtkSp20Flb86H5uIr0rTLPJsmmMDzlJwkFRCHnh7ePvYJZI9zE=",
				"SmzTeGYc5m0GhSza0xglRQ==");

		System.out.println(JsonUtil.create().toJson(userInfo));

	}

}
