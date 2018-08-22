package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysOpsCmpTaskDao;
import net.aicoder.devp.business.sys.dao.DevpSysOpsCmpTaskSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysOpsCmpTask;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsCmpTaskService")
public class DevpSysOpsCmpTaskService  extends GenericCrudService<DevpSysOpsCmpTask, Long, DevpSysOpsCmpTaskCondition, DevpSysOpsCmpTaskDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsCmpTaskService.class);

	@Override
	public Specification<DevpSysOpsCmpTask> getSpecification(DevpSysOpsCmpTaskCondition condition) {
		return new DevpSysOpsCmpTaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsCmpTask.PROPERTY_TID);
		return sort;
	}
}