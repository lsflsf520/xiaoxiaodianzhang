package com.fengshang.xiaodianbao.uc.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.tools.common.bean.IUser;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.common.constant.Sex;
import com.xyz.tools.db.bean.BaseEntity;

public class WxUser extends BaseEntity<Integer> implements IUser {
	private Integer id;

	private String nickName;

	private Sex sex;

	private String headImg;

	private String openId;

	private String unionId;

	private String shopMgr;

	private String phone;

	private Integer posterId;

	private CommonStatus status;

	private Date createTime;

	private Date lastUptime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId == null ? null : unionId.trim();
	}

	public String getShopMgr() {
		return shopMgr;
	}

	public void setShopMgr(String shopMgr) {
		this.shopMgr = shopMgr == null ? null : shopMgr.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getPosterId() {
		return posterId;
	}

	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}

	public CommonStatus getStatus() {
		return status;
	}

	public void setStatus(CommonStatus status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUptime() {
		return lastUptime;
	}

	public void setLastUptime(Date lastUptime) {
		this.lastUptime = lastUptime;
	}

	@Override
	@JsonIgnore
	public Integer getPK() {
		return id;
	}

	@Override
	@JsonIgnore
	public long getUid() {
		return this.getId();
	}

	@Override
	@JsonIgnore
	public int getUidInt() {
		return this.getId();
	}

	@Override
	@JsonIgnore
	public String getRealName() {
		return this.getNickName();
	}

	@Override
	@JsonIgnore
	public String getEmail() {
		return null;
	}

	@Override
	@JsonIgnore
	public String getShowName() {
		return this.getRealName();
	}
}