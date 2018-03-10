package com.fengshang.xiaodianbao.shop.service;

import com.fengshang.xiaodianbao.shop.dao.CouponTmplDao;
import com.fengshang.xiaodianbao.shop.entity.CouponTmpl;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CouponTmplService extends AbstractBaseService<Integer, CouponTmpl> {
    @Resource
    private CouponTmplDao couponTmplDao;

    @Override
    protected IBaseDao<Integer, CouponTmpl> getBaseDao() {
        return couponTmplDao;
    }

    public Integer insertReturnPK(CouponTmpl couponTmpl) {
        couponTmplDao.insertReturnPK(couponTmpl);
        return couponTmpl.getPK();
    }

    public Integer doSave(CouponTmpl couponTmpl) {
        if (couponTmpl.getPK() == null) {
            return this.insertReturnPK(couponTmpl);
        }
        this.update(couponTmpl);
        return couponTmpl.getPK();
    }
}