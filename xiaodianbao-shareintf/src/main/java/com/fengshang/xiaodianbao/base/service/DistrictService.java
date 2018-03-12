package com.fengshang.xiaodianbao.base.service;

import com.fengshang.xiaodianbao.base.dao.DistrictDao;
import com.fengshang.xiaodianbao.base.entity.District;
import com.xyz.tools.cache.constant.DefaultCacheNS;
import com.xyz.tools.cache.eh.EhCacheTool;
import com.xyz.tools.common.bean.DataTree;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class DistrictService extends AbstractBaseService<Integer, District> {
    @Resource
    private DistrictDao districtDao;

    @Override
    protected IBaseDao<Integer, District> getBaseDao() {
        return districtDao;
    }

    public Integer insertReturnPK(District district) {
        districtDao.insertReturnPK(district);
        return district.getPK();
    }

    public Integer doSave(District district) {
        if (district.getPK() == null) {
            return this.insertReturnPK(district);
        }
        this.update(district);
        return district.getPK();
    }
    
    public List<District> findAll() {
       	List<District> cacheDatas = EhCacheTool.getValue(DefaultCacheNS.BASE_DISTRICT);
       	if(CollectionUtils.isEmpty(cacheDatas)) {
       		cacheDatas = super.findAll("priority.desc");
       		
       		if(!CollectionUtils.isEmpty(cacheDatas)) {
       			EhCacheTool.put(DefaultCacheNS.BASE_DISTRICT, cacheDatas);
       		}
       	}
       	
       	return cacheDatas;
    }
    
    public List<District> loadByParentId(int parentId) {
      	List<District> cacheDatas = findAll();
      	DataTree<Integer, District> dataTree = new DataTree<Integer, District>(cacheDatas);
      	
      	if(parentId <= 0) {
      		return dataTree.getRootOnly();
      	}
      	
      	District dataNode = dataTree.getNode(parentId);
      	if(dataNode != null) {
      		List<District> children = dataNode.getChildren();
      		for(District child : children) {
      			child.clearChild();
      		}
      		
      		return children;
      	}
      	
      	return new ArrayList<>();
    }
    
}