package com.fengshang.xiaodianbao.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

			shop.setCheckState(CheckState.CHECKING);
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

	/**
	 * 根据 审核通过、审核中、已拒绝 的先后优先级来获取自己的店铺
	 * 
	 * @param wxUid
	 * @return
	 */
	public Shop loadMyFirstShop(int wxUid) {
		Shop query = new Shop();
		query.setWxUid(wxUid);

		Shop checkingShop = null;
		Shop rejectShop = null;
		List<Shop> dbDatas = this.findByEntity(query);
		if (!CollectionUtils.isEmpty(dbDatas)) {
			for (Shop dbData : dbDatas) {
				if (CheckState.PASSED.equals(dbData.getCheckState())) {
					return dbData;
				} else if (CheckState.CHECKING.equals(dbData.getCheckState())) {
					checkingShop = dbData;
				} else if (CheckState.REJECT.equals(dbData.getCheckState())) {
					rejectShop = dbData;
				}
			}
		}

		return checkingShop != null ? checkingShop : (rejectShop != null ? rejectShop : null);
	}

	public Map<Integer, Shop> loadShops(Integer... shopIds) {
		List<Shop> dbDatas = this.findByIds(shopIds);

		return BeanUtils.buildPK2BeanMap(dbDatas);
	}

	public CheckState loadMyFirstShopState(int wxUid) {
		Shop dbData = loadMyFirstShop(wxUid);

		return dbData == null ? null : dbData.getCheckState();
	}
}