package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysCmpResDao;
import net.aicoder.devp.business.sys.dao.DevpSysCmpResSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysCmpRes;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysCmpResService")
public class DevpSysCmpResService  extends GenericCrudService<DevpSysCmpRes, Long, DevpSysCmpResCondition, DevpSysCmpResDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpResService.class);

	@Override
	public Specification<DevpSysCmpRes> getSpecification(DevpSysCmpResCondition condition) {
		return new DevpSysCmpResSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysCmpRes.PROPERTY_TID);
		return sort;
	}
}