package net.aicoder.devp.deploy.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsAssetCmdbDao;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsAssetCmdbSpecification;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsAssetCmdb;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsAssetCmdbService")
public class DevpOpsAssetCmdbService  extends CrudService<DevpOpsAssetCmdb, DevpOpsAssetCmdbCondition, DevpOpsAssetCmdbDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbService.class);

	@Override
	public Specification<DevpOpsAssetCmdb> getSpecification(DevpOpsAssetCmdbCondition condition) {
		return new DevpOpsAssetCmdbSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsAssetCmdb.PROPERTY_TID);
		return sort;
	}
}