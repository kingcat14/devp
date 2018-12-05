package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.env.dao.MachineDao;
import net.aicoder.speedcloud.business.env.dao.MachineSpecification;
import net.aicoder.speedcloud.business.env.domain.Machine;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("machineService")
@Slf4j
public class MachineService  extends GenericCrudService<Machine, String, MachineCondition, MachineDao> {

	@Override
	public Specification<Machine> getSpecification(MachineCondition condition) {
		return new MachineSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, Machine.PROPERTY_MODIFY_AT);

		return sort;
	}
}