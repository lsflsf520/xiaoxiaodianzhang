package com.fengshang.xiaodianbao.constant;

public enum DiscountType {

	Amount("面额"), Discount("折扣"),;

	private String descp;

	private DiscountType(String descp) {
		this.descp = descp;
	}

	public String getDescp() {
		return descp;
	}

}
