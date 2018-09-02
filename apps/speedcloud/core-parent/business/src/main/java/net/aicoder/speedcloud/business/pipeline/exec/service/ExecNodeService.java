package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.ExecNodeDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.ExecNodeSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.ExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("execNodeService")
public class ExecNodeService  extends GenericCrudService<ExecNode, Long, ExecNodeCondition, ExecNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeService.class);

	@Override
	public Specification<ExecNode> getSpecification(ExecNodeCondition condition) {
		return new ExecNodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ExecNode.PROPERTY_CODE);
		return sort;
	}
}