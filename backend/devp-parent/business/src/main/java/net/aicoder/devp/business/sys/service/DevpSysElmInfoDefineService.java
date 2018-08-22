package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysElmInfoDefineDao;
import net.aicoder.devp.business.sys.dao.DevpSysElmInfoDefineSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysElmInfoDefine;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysElmInfoDefineService")
public class DevpSysElmInfoDefineService  extends GenericCrudService<DevpSysElmInfoDefine, Long, DevpSysElmInfoDefineCondition, DevpSysElmInfoDefineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInfoDefineService.class);

	@Override
	public Specification<DevpSysElmInfoDefine> getSpecification(DevpSysElmInfoDefineCondition condition) {
		return new DevpSysElmInfoDefineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysElmInfoDefine.PROPERTY_TID);
		return sort;
	}
}