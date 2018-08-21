package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysElementInfoDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysElementInfoSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysElementInfo;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElementInfoService")
public class DevpSysElementInfoService  extends CrudService<DevpSysElementInfo, DevpSysElementInfoCondition, DevpSysElementInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementInfoService.class);

	@Override
	public Specification<DevpSysElementInfo> getSpecification(DevpSysElementInfoCondition condition) {
		return new DevpSysElementInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysElementInfo.PROPERTY_TID);
		return sort;
	}
}