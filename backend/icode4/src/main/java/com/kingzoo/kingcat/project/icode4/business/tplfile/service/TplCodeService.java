package com.kingzoo.kingcat.project.icode4.business.tplfile.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.tplfile.dao.TplCodeDao;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
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

import java.util.List;


@Service("tplCodeService")
public class TplCodeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplCode.class);

	@Autowired
	@Qualifier("tplCodeDao")
	private TplCodeDao tplCodeDao;

	@Transactional
	public void add(TplCode tplCode){
		tplCode.setId(IDGenerator.get());
		tplCodeDao.insert(tplCode);
	}

	@Transactional
	public void add(List<TplCode> tplCodeList){
		for(TplCode tplCode:tplCodeList){
			this.add(tplCode);
		}
	}

	@Transactional
	public void delete(TplCode tplCode){
	    LOGGER.debug("delete tplCode:{}", tplCode);
		tplCodeDao.delete(tplCode.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete TplCode by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete tplCode:{}", id);
		tplCodeDao.delete(id);
	}

	@Transactional
	public void delete(List<TplCode> tplCodes){
		for(TplCode tplCode: tplCodes){
			delete(tplCode.getId());
		}
	}

	@Transactional
	public void merge(TplCode tplCode){
		tplCodeDao.save(tplCode);
	}

	@Transactional
	public void merge(List<TplCode> tplCodeList){
		for(TplCode tplCode:tplCodeList){
			this.merge(tplCode);
		}
	}

	@Transactional(readOnly=true)
	public TplCode find(String id){
	    TplCode tplCode = null;
		if(StringUtils.isNotEmpty(id)) {
            tplCode = tplCodeDao.findOne(id);
        }

		return tplCode;
	}

	@Transactional(readOnly=true)
	public TplCode findOne(TplCodeCondition tplCodeCondition){

		List<TplCode> tplCodeList = tplCodeDao.findAll(tplCodeCondition);

		TplCode result = null;
		if(CollectionUtils.isNotEmpty(tplCodeList)){
			result = tplCodeList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<TplCode> findAll(TplCodeCondition tplCodeCondition){

		List<TplCode> tplCodeList = tplCodeDao.findAll(tplCodeCondition, getDefaultSort());

		return tplCodeList;
	}

	@Transactional(readOnly=true)
	public Page<TplCode> find(Pageable pageable){
		Page<TplCode> tplCodeList = tplCodeDao.findAll(pageable, null, getDefaultSort());
		return tplCodeList;
	}

	@Transactional(readOnly=true)
	public Page<TplCode> find(Pageable pageable, TplCodeCondition tplCodeCondition){
		Page<TplCode> tplCodeList = tplCodeDao.findAll(pageable, tplCodeCondition, getDefaultSort());
		return tplCodeList;
	}

	@Transactional(readOnly=true)
	public long count(TplCodeCondition tplCodeCondition){
		long count = tplCodeDao.count(tplCodeCondition);
		return count;
	}

	public TplCode copy(String id){
		if(StringUtils.isEmpty(id)){
			return null;
		}
		TplCode tplCode = tplCodeDao.findOne(id);

		if(tplCode == null){
			return null;
		}

		TplCode nCodeTpl = new TplCode();
		BeanUtils.copyProperties(tplCode, nCodeTpl);
		nCodeTpl.setId(IDGenerator.get());
		tplCodeDao.save(nCodeTpl);

		return nCodeTpl;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TplCode.PROPERTY_NAME);
		return sort;
	}


	/**
	 * 得到一个代码集合中的所有模板类型
	 * @param typeSetId
	 * @return
	 */
	public List<String> findCodeTplTypes(String typeSetId){
		return this.tplCodeDao.findCodeTplTypes(typeSetId);
	}




}