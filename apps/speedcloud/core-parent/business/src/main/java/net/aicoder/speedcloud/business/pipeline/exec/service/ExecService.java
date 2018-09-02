package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.ExecDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.ExecSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.Exec;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("execService")
public class ExecService  extends GenericCrudService<Exec, Long, ExecCondition, ExecDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecService.class);

	@Override
	public Specification<Exec> getSpecification(ExecCondition condition) {
		return new ExecSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Exec.PROPERTY_CODE);
		return sort;
	}
}