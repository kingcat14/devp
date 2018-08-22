package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysParasDefineDao;
import net.aicoder.devp.business.sys.dao.DevpSysParasDefineSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysParasDefine;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysParasDefineService")
public class DevpSysParasDefineService  extends GenericCrudService<DevpSysParasDefine, Long, DevpSysParasDefineCondition, DevpSysParasDefineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysParasDefineService.class);

	@Override
	public Specification<DevpSysParasDefine> getSpecification(DevpSysParasDefineCondition condition) {
		return new DevpSysParasDefineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysParasDefine.PROPERTY_TID);
		return sort;
	}
}