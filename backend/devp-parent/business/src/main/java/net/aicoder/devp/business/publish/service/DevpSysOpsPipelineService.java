package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipelineDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipelineSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeline;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipelineService")
public class DevpSysOpsPipelineService  extends GenericCrudService<DevpSysOpsPipeline, Long, DevpSysOpsPipelineCondition, DevpSysOpsPipelineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipelineService.class);

	@Override
	public Specification<DevpSysOpsPipeline> getSpecification(DevpSysOpsPipelineCondition condition) {
		return new DevpSysOpsPipelineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsPipeline.PROPERTY_TID);
		return sort;
	}
}