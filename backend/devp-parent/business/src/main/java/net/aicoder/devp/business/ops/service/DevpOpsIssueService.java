package net.aicoder.devp.business.ops.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.ops.dao.DevpOpsIssueDao;
import net.aicoder.devp.business.ops.dao.DevpOpsIssueSpecification;
import net.aicoder.devp.business.ops.domain.DevpOpsIssue;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsIssueService")
public class DevpOpsIssueService  extends GenericCrudService<DevpOpsIssue, Long, DevpOpsIssueCondition, DevpOpsIssueDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueService.class);

	@Override
	public Specification<DevpOpsIssue> getSpecification(DevpOpsIssueCondition condition) {
		return new DevpOpsIssueSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpOpsIssue.PROPERTY_TID);
		return sort;
	}
}