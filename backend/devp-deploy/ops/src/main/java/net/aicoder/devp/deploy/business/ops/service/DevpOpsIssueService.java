package net.aicoder.devp.deploy.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsIssueDao;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsIssueSpecification;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsIssue;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsIssueService")
public class DevpOpsIssueService  extends CrudService<DevpOpsIssue, DevpOpsIssueCondition, DevpOpsIssueDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueService.class);

	@Override
	public Specification<DevpOpsIssue> getSpecification(DevpOpsIssueCondition condition) {
		return new DevpOpsIssueSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsIssue.PROPERTY_TID);
		return sort;
	}
}