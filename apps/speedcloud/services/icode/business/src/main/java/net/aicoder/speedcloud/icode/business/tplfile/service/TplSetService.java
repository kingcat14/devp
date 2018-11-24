package net.aicoder.speedcloud.icode.business.tplfile.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplSetDao;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplSetSpecification;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplSet;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tplSetService")
@Slf4j
public class TplSetService  extends GenericCrudService<TplSet, String, TplSetCondition, TplSetDao> {

	@Override
	public Specification<TplSet> getSpecification(TplSetCondition condition) {
		return new TplSetSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, TplSet.PROPERTY_CODE);
		return sort;
	}
}