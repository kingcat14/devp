package net.aicoder.speedcloud.icode.business.tplfile.service;


import com.yunkang.saas.common.framework.util.IDGenerator;
import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplCodeDao;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplCodeSpecification;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tplCodeService")
@Slf4j
public class TplCodeService  extends GenericCrudService<TplCode, String, TplCodeCondition, TplCodeDao> {

	public TplCode copy(String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		TplCode tplCode = dao.findOne(id);

		if(tplCode == null){
			return null;
		}

		TplCode nCodeTpl = new TplCode();
		BeanUtils.copyProperties(tplCode, nCodeTpl);
		nCodeTpl.setId(IDGenerator.uuid());
		dao.save(nCodeTpl);

		return nCodeTpl;
	}


	@Override
	public Specification<TplCode> getSpecification(TplCodeCondition condition) {
		return new TplCodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, TplCode.PROPERTY_CODE);
		return sort;
	}
}