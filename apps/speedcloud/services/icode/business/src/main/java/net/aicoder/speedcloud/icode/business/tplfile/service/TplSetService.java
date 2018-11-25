package net.aicoder.speedcloud.icode.business.tplfile.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplSetDao;
import net.aicoder.speedcloud.icode.business.tplfile.dao.TplSetSpecification;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplSet;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("tplSetService")
@Slf4j
public class TplSetService  extends GenericCrudService<TplSet, String, TplSetCondition, TplSetDao> {

	@Autowired
	private TplCodeService tplCodeService;

	@Transactional
	public TplSet copy(String id){

		//1.先复制模板集名称
		TplSet tplSet = this.find(id);

		TplSet nTplSet = new TplSet();

		BeanUtils.copyProperties(tplSet, nTplSet);
		nTplSet.setName("Copy_"+tplSet.getName());
		nTplSet.setCode(tplSet.getCode());
		nTplSet.setId(null);

		this.add(nTplSet);

		TplCodeCondition codeTplCondition = new TplCodeCondition();
		codeTplCondition.setTplSet(tplSet.getId());
		List<TplCode> codeTplList = tplCodeService.findAll(codeTplCondition);

		List<TplCode> nList = new ArrayList<>();
		for(TplCode codeTpl : codeTplList){
			TplCode nCodeTpl = new TplCode();
			BeanUtils.copyProperties(codeTpl, nCodeTpl);
			nCodeTpl.setId(null);
			nCodeTpl.setTplSet(nTplSet.getId());
			nList.add(nCodeTpl);
		}

		tplCodeService.add(nList);

		return tplSet;

	}


	@Override
	public Specification<TplSet> getSpecification(TplSetCondition condition) {
		return new TplSetSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, TplSet.PROPERTY_CODE);
		return sort;
	}
}