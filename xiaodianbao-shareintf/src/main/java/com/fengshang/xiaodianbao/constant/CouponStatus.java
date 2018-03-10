package com.fengshang.xiaodianbao.constant;

public enum CouponStatus {

	DELETED("已删除"),
	EXPIRED("已过期"),
	RECVD("已领取"),
	USED("已使用"),
	;
	
	private String descp;
	private CouponStatus(String descp){
		this.descp = descp;
	}
	public String getDescp() {
		return descp;
	}
	
}
