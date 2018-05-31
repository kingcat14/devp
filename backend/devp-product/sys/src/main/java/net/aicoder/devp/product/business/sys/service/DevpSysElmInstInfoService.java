package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmInstInfoDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysElmInstInfoSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstInfo;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElmInstInfoService")
public class DevpSysElmInstInfoService  extends CrudService<DevpSysElmInstInfo, DevpSysElmInstInfoCondition, DevpSysElmInstInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstInfoService.class);

	@Override
	public Specification<DevpSysElmInstInfo> getSpecification(DevpSysElmInstInfoCondition condition) {
		return new DevpSysElmInstInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysElmInstInfo.PROPERTY_TID);
		return sort;
	}
}