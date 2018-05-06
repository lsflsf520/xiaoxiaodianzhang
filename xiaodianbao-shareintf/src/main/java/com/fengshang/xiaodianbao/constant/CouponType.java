package com.fengshang.xiaodianbao.constant;

public enum CouponType {

	// Discount("折扣券"),
	Diyong("抵用券"), Cash("现金券"),;

	private String descp;

	private CouponType(String descp) {
		this.descp = descp;
	}

	public String getDescp() {
		return descp;
	}

}
