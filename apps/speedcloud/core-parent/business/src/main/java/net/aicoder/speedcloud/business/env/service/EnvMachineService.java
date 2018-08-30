package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.env.dao.EnvMachineDao;
import net.aicoder.speedcloud.business.env.dao.EnvMachineSpecification;
import net.aicoder.speedcloud.business.env.domain.EnvMachine;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("envMachineService")
public class EnvMachineService  extends GenericCrudService<EnvMachine, Long, EnvMachineCondition, EnvMachineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvMachineService.class);

	@Override
	public Specification<EnvMachine> getSpecification(EnvMachineCondition condition) {
		return new EnvMachineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, EnvMachine.PROPERTY_TID);
		return sort;
	}
}