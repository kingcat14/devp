package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.AppBaseInfoDao;
import net.aicoder.speedcloud.business.app.dao.AppBaseInfoSpecification;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appBaseInfoService")
@Slf4j
public class AppBaseInfoService  extends GenericCrudService<AppBaseInfo, String, AppBaseInfoCondition, AppBaseInfoDao> {

	@Override
	public Specification<AppBaseInfo> getSpecification(AppBaseInfoCondition condition) {
		return new AppBaseInfoSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, AppBaseInfo.PROPERTY_MODIFY_AT);

		return sort;
	}
}