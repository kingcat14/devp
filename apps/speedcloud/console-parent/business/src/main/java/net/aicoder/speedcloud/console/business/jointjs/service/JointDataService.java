package net.aicoder.speedcloud.console.business.jointjs.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.console.business.jointjs.dao.JointDataDao;
import net.aicoder.speedcloud.console.business.jointjs.dao.JointDataSpecification;
import net.aicoder.speedcloud.console.business.jointjs.domain.JointData;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("jointDataService")
public class JointDataService  extends GenericCrudService<JointData, String, JointDataCondition, JointDataDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JointDataService.class);

	@Override
	public Specification<JointData> getSpecification(JointDataCondition condition) {
		return new JointDataSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, JointData.PROPERTY_SCHEME);
		return sort;
	}
}