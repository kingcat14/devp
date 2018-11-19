package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.env.dao.MachineDao;
import net.aicoder.speedcloud.business.env.dao.MachineSpecification;
import net.aicoder.speedcloud.business.env.domain.Machine;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("machineService")
public class MachineService  extends GenericCrudService<Machine, Long, MachineCondition, MachineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineService.class);

	@Override
	public Specification<Machine> getSpecification(MachineCondition condition) {
		return new MachineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Machine.PROPERTY_TID);
		return sort;
	}
}