package com.fengshang.xiaodianbao.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.shop.dao.ShopDao;
import com.fengshang.xiaodianbao.shop.entity.Shop;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.xyz.tools.common.constant.CheckState;
import com.xyz.tools.common.utils.BeanUtils;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ShopService extends AbstractBaseService<Integer, Shop> {
	@Resource
	private ShopDao shopDao;

	@Override
	protected IBaseDao<Integer, Shop> getBaseDao() {
		return shopDao;
	}

	public Integer insertReturnPK(Shop shop) {
		shopDao.insertReturnPK(shop);
		return shop.getPK();
	}

	public Integer doSave(Shop shop) {
		shop.setLastUptime(new Date());
		if (shop.getPK() == null) {

			shop.setCheckState(CheckState.PASSED);
			shop.setCreateTime(shop.getLastUptime());
			return this.insertReturnPK(shop);
		}
		this.update(shop);
		return shop.getPK();
	}

	public List<Shop> loadMyShops(int wxUid) {
		Shop query = new Shop();
		query.setWxUid(wxUid);

		return this.findByEntity(query, "last_uptime", Direction.DESC);
	}

	public Shop loadMyFirstShop(int wxUid) {
		Shop query = new Shop();
		query.setWxUid(wxUid);

		return this.findOne(query);
	}

	public Map<Integer, Shop> loadShops(Integer... shopIds) {
		List<Shop> dbDatas = this.findByIds(shopIds);

		return BeanUtils.buildPK2BeanMap(dbDatas);
	}
}