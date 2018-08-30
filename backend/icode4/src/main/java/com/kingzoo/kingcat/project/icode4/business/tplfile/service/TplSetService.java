package com.kingzoo.kingcat.project.icode4.business.tplfile.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.tplfile.dao.TplSetDao;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplSetCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("tplSetService")
public class TplSetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplSet.class);

	@Autowired
	@Qualifier("tplSetDao")
	private TplSetDao tplSetDao;


	@Autowired
	private TplCodeService tplCodeService;

	@Transactional
	public void add(TplSet tplSet){
		tplSet.setId(IDGenerator.get());
		tplSetDao.insert(tplSet);
	}

	@Transactional
	public void add(List<TplSet> tplSetList){
		for(TplSet tplSet:tplSetList){
			this.add(tplSet);
		}
	}

	@Transactional
	public void delete(TplSet tplSet){
	    LOGGER.debug("delete tplSet:{}", tplSet);
		tplSetDao.delete(tplSet.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete TplSet by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete tplSet:{}", id);
		tplSetDao.delete(id);
	}

	@Transactional
	public void delete(List<TplSet> tplSets){
		for(TplSet tplSet: tplSets){
			delete(tplSet.getId());
		}
	}

	@Transactional
	public void merge(TplSet tplSet){
		tplSetDao.save(tplSet);
	}

	@Transactional
	public void merge(List<TplSet> tplSetList){
		for(TplSet tplSet:tplSetList){
			this.merge(tplSet);
		}
	}

	@Transactional(readOnly=true)
	public TplSet find(String id){
	    TplSet tplSet = null;
		if(StringUtils.isNotEmpty(id)) {
            tplSet = tplSetDao.findOne(id);
        }

		return tplSet;
	}

	@Transactional(readOnly=true)
	public TplSet findOne(TplSetCondition tplSetCondition){

		List<TplSet> tplSetList = tplSetDao.findAll(tplSetCondition);

		TplSet result = null;
		if(CollectionUtils.isNotEmpty(tplSetList)){
			result = tplSetList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<TplSet> findAll(TplSetCondition tplSetCondition){

		List<TplSet> tplSetList = tplSetDao.findAll(tplSetCondition, getDefaultSort());

		return tplSetList;
	}

	@Transactional(readOnly=true)
	public Page<TplSet> find(Pageable pageable){
		Page<TplSet> tplSetList = tplSetDao.findAll(pageable, null, getDefaultSort());
		return tplSetList;
	}

	@Transactional(readOnly=true)
	public Page<TplSet> find(Pageable pageable, TplSetCondition tplSetCondition){
		Page<TplSet> tplSetList = tplSetDao.findAll(pageable, tplSetCondition, getDefaultSort());
		return tplSetList;
	}

	@Transactional(readOnly=true)
	public long count(TplSetCondition tplSetCondition){
		long count = tplSetDao.count(tplSetCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TplSet.PROPERTY_NAME);
		return sort;
	}



	@Transactional
	public TplSet copy(String id){

		//1.先复制模板集名称
		TplSet tplSet = this.find(id);


		TplSet nTplSet = new TplSet();
		nTplSet.setName("Copy_"+tplSet.getName());
		nTplSet.setCode(tplSet.getCode());
		this.add(nTplSet);


		TplCodeCondition codeTplCondition = new TplCodeCondition();
		codeTplCondition.setTplSetId(tplSet.getId()+"");
		List<TplCode> codeTplList = tplCodeService.findAll(codeTplCondition);

		List<TplCode> nList = new ArrayList<>();
		for(TplCode codeTpl : codeTplList){
			TplCode nCodeTpl = new TplCode();
			BeanUtils.copyProperties(codeTpl, nCodeTpl);
			nCodeTpl.setId(null);
			nCodeTpl.setTplSetId(nTplSet.getId()+"");
			nList.add(nCodeTpl);

		}

		tplCodeService.add(nList);

		return tplSet;

	}




}