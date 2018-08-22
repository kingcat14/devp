package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipeNodeDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipeNodeSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeNode;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipeNodeService")
public class DevpSysOpsPipeNodeService  extends GenericCrudService<DevpSysOpsPipeNode, Long, DevpSysOpsPipeNodeCondition, DevpSysOpsPipeNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeNodeService.class);

	@Override
	public Specification<DevpSysOpsPipeNode> getSpecification(DevpSysOpsPipeNodeCondition condition) {
		return new DevpSysOpsPipeNodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsPipeNode.PROPERTY_TID);
		return sort;
	}
}