package net.aicoder.speedcloud.business.env.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.env.dao.EnvMachineDao;
import net.aicoder.speedcloud.business.env.dao.EnvMachineSpecification;
import net.aicoder.speedcloud.business.env.domain.EnvMachine;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("envMachineService")
@Slf4j
public class EnvMachineService  extends GenericCrudService<EnvMachine, Long, EnvMachineCondition, EnvMachineDao> {

	@Override
	public Specification<EnvMachine> getSpecification(EnvMachineCondition condition) {
		return new EnvMachineSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, EnvMachine.PROPERTY_MODIFY_AT);

		return sort;
	}
}