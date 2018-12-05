package net.aicoder.speedcloud.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.dao.ApplicationTypeDao;
import net.aicoder.speedcloud.business.app.dao.ApplicationTypeSpecification;
import net.aicoder.speedcloud.business.app.domain.ApplicationType;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("applicationTypeService")
@Slf4j
public class ApplicationTypeService  extends GenericCrudService<ApplicationType, String, ApplicationTypeCondition, ApplicationTypeDao> {

    @Transactional
    public void add(ApplicationType t){
        t.setCode(t.getCode());
        dao.save(t);
    }


    @Override
	public Specification<ApplicationType> getSpecification(ApplicationTypeCondition condition) {
		return new ApplicationTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.ASC, ApplicationType.PROPERTY_IDX);

		return sort;
	}
}