package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.AppDevelopConfigDao;
import net.aicoder.speedcloud.business.app.dao.AppDevelopConfigSpecification;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appDevelopConfigService")
@Slf4j
public class AppDevelopConfigService  extends GenericCrudService<AppDevelopConfig, String, AppDevelopConfigCondition, AppDevelopConfigDao> {

	/**
	 * 根据APP_ID查询
	 * @param appId
	 * @return
	 */
	public AppDevelopConfig findByApp(String appId){

		return dao.findByApp(appId);

	}
	@Override
	public Specification<AppDevelopConfig> getSpecification(AppDevelopConfigCondition condition) {
		return new AppDevelopConfigSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, AppDevelopConfig.PROPERTY_MODIFY_AT);

		return sort;
	}
}