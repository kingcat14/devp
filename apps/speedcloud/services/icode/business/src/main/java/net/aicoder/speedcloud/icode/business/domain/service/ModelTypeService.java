package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.ModelTypeDao;
import net.aicoder.speedcloud.icode.business.domain.dao.ModelTypeSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.ModelType;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("modelTypeService")
@Slf4j
public class ModelTypeService  extends GenericCrudService<ModelType, String, ModelTypeCondition, ModelTypeDao> {

	@Override
	public Specification<ModelType> getSpecification(ModelTypeCondition condition) {
		return new ModelTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, ModelType.PROPERTY_IDX);
		return sort;
	}
}