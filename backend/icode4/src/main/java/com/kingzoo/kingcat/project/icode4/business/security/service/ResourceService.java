package com.kingzoo.kingcat.project.icode4.business.security.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceAddDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceEditDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceSearchDto;
import com.kingzoo.kingcat.project.icode4.business.security.entity.Resource;
import com.kingzoo.kingcat.project.icode4.business.security.dao.ResourceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 资源服务
 */
@Service("resourceService")
public class ResourceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceService.class);

	@Autowired
	@Qualifier("resourceDao")
	private ResourceDao resourceDao;

	@Transactional
	public Resource add(ResourceAddDto resourceAddDto){
	    Resource resource = new Resource();
	    BeanUtils.copyProperties(resourceAddDto, resource);
	    resource.setId(IDGenerator.get());
	    resource.setCreateAt(new Date());
	    resource.setModifyAt(resource.getCreateAt());
	    resource.setModifyUid(resource.getCreateUid());
	    resource.setModifyUname(resource.getCreateUname());

	    resourceDao.insert(resource);
	    return resource;
	}

	@Transactional
	public void delete(String id, String handlerId){
		if(id == null){
			LOGGER.warn("try delete Resource by empty id.");
			return;
		}
	    LOGGER.debug("delete resource:{}", id);
		resourceDao.delete(id);
	}

	@Transactional
	public Resource update(ResourceEditDto resourceEditDto){
		Resource resource = this.find(resourceEditDto.getId());
		String tenantId = resource.getTenantId();
        BeanUtils.copyProperties(resourceEditDto, resource);
        resource.setModifyAt(new Date());
        resource.setTenantId(tenantId);
		resourceDao.save(resource);
		return resource;
	}

	@Transactional(readOnly=true)
	public Resource find(String id){
	    Resource resource = null;
		if(id != null){
            resource = resourceDao.findOne(id);
        }

		return resource;
	}
	
	public Page<Resource> search(ResourceSearchDto resourceSearchDto){
    
        Pageable pageable = new PageRequest(resourceSearchDto.getPage(), resourceSearchDto.getPageSize(), getDefaultSort());

        return resourceDao.findAll(pageable, resourceSearchDto);

    }


    public Sort getDefaultSort(){
        Sort sort = new Sort(Sort.Direction.ASC, Resource.PROPERTY_ORDER_INDEX);
        return sort;
    }


}