package com.fengshang.xiaodianbao.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.XdConstant;
import com.fengshang.xiaodianbao.data.dao.DataMonthShopDao;
import com.fengshang.xiaodianbao.data.entity.DataMonthShop;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class DataMonthShopService extends AbstractBaseService<Integer, DataMonthShop> {
	@Resource
	private DataMonthShopDao dataMonthShopDao;

	@Override
	protected IBaseDao<Integer, DataMonthShop> getBaseDao() {
		return dataMonthShopDao;
	}

	public Integer insertReturnPK(DataMonthShop dataMonthShop) {
		dataMonthShopDao.insertReturnPK(dataMonthShop);
		return dataMonthShop.getPK();
	}

	public Integer doSave(DataMonthShop dataMonthShop) {
		if (dataMonthShop.getPK() == null) {
			return this.insertReturnPK(dataMonthShop);
		}
		this.update(dataMonthShop);
		return dataMonthShop.getPK();
	}

	public List<DataMonthShop> loadLatestData(int shopId, CouponType couponType) {
		DataMonthShop queryData = new DataMonthShop();
		queryData.setShopId(shopId);
		queryData.setCouponType(couponType);
		queryData.setLimit(XdConstant.MAX_DATA_MONTHS);

		return this.findByEntity(queryData, "data_day", Direction.DESC);
	}
}