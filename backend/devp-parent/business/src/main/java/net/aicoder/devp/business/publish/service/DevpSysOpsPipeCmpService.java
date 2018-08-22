package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipeCmpDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipeCmpSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeCmp;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipeCmpService")
public class DevpSysOpsPipeCmpService  extends GenericCrudService<DevpSysOpsPipeCmp, Long, DevpSysOpsPipeCmpCondition, DevpSysOpsPipeCmpDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeCmpService.class);

	@Override
	public Specification<DevpSysOpsPipeCmp> getSpecification(DevpSysOpsPipeCmpCondition condition) {
		return new DevpSysOpsPipeCmpSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsPipeCmp.PROPERTY_TID);
		return sort;
	}
}