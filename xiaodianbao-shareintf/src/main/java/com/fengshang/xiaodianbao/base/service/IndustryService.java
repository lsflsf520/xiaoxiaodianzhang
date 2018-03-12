package com.fengshang.xiaodianbao.base.service;

import com.fengshang.xiaodianbao.base.dao.IndustryDao;
import com.fengshang.xiaodianbao.base.entity.District;
import com.fengshang.xiaodianbao.base.entity.Industry;
import com.xyz.tools.cache.constant.DefaultCacheNS;
import com.xyz.tools.cache.eh.EhCacheTool;
import com.xyz.tools.common.bean.DataTree;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class IndustryService extends AbstractBaseService<Integer, Industry> {
    @Resource
    private IndustryDao industryDao;

    @Override
    protected IBaseDao<Integer, Industry> getBaseDao() {
        return industryDao;
    }

    public Integer insertReturnPK(Industry industry) {
        industryDao.insertReturnPK(industry);
        return industry.getPK();
    }

    public Integer doSave(Industry industry) {
        if (industry.getPK() == null) {
            return this.insertReturnPK(industry);
        }
        this.update(industry);
        return industry.getPK();
    }
    
    public List<Industry> findAvail() {
       	List<Industry> cacheDatas = EhCacheTool.getValue(DefaultCacheNS.BASE_INDUSTRY);
       	if(CollectionUtils.isEmpty(cacheDatas)) {
       		Industry query = new Industry();
       		query.setStatus(CommonStatus.NORMAL);
       		cacheDatas = this.findByEntity(query, "priority.desc");
       		
       		if(!CollectionUtils.isEmpty(cacheDatas)) {
       			EhCacheTool.put(DefaultCacheNS.BASE_INDUSTRY, cacheDatas);
       		}
       	}
       	
       	return cacheDatas;
    }
    
    public List<Industry> loadByParentId(int parentId) {
      	List<Industry> cacheDatas = findAvail();
      	DataTree<Integer, Industry> dataTree = new DataTree<Integer, Industry>(cacheDatas);
      	
      	if(parentId <= 0) {
      		return dataTree.getRootOnly();
      	}
      	
      	Industry dataNode = dataTree.getNode(parentId);
      	if(dataNode != null) {
      		List<Industry> children = dataNode.getChildren();
      		for(Industry child : children) {
      			child.clearChild();
      		}
      		
      		return children;
      	}
      	
      	return new ArrayList<>();
    }
}