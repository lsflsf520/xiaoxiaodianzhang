package com.fengshang.xiaodianbao.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fengshang.xiaodianbao.constant.CouponType;
import com.fengshang.xiaodianbao.constant.XdConstant;
import com.fengshang.xiaodianbao.data.dao.DataDayShopDao;
import com.fengshang.xiaodianbao.data.entity.DataDayShop;
import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class DataDayShopService extends AbstractBaseService<Integer, DataDayShop> {
	@Resource
	private DataDayShopDao dataDayShopDao;

	@Override
	protected IBaseDao<Integer, DataDayShop> getBaseDao() {
		return dataDayShopDao;
	}

	public Integer insertReturnPK(DataDayShop dataDayShop) {
		dataDayShopDao.insertReturnPK(dataDayShop);
		return dataDayShop.getPK();
	}

	public Integer doSave(DataDayShop dataDayShop) {
		if (dataDayShop.getPK() == null) {
			return this.insertReturnPK(dataDayShop);
		}
		this.update(dataDayShop);
		return dataDayShop.getPK();
	}

	public List<DataDayShop> loadLatestData(int shopId, CouponType couponType) {
		DataDayShop queryData = new DataDayShop();
		queryData.setShopId(shopId);
		queryData.setCouponType(couponType);
		queryData.setLimit(XdConstant.MAX_DATA_DAYS);

		return this.findByEntity(queryData, "data_day", Direction.DESC);
	}

}