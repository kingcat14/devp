package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.dao.AppBaseInfoDao;
import net.aicoder.speedcloud.business.app.dao.AppBaseInfoSpecification;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appBaseInfoService")
public class AppBaseInfoService  extends GenericCrudService<AppBaseInfo, Long, AppBaseInfoCondition, AppBaseInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoService.class);

	@Override
	public Specification<AppBaseInfo> getSpecification(AppBaseInfoCondition condition) {
		return new AppBaseInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AppBaseInfo.PROPERTY_TID);
		return sort;
	}
}