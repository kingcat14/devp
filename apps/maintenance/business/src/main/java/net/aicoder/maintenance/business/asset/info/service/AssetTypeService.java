package net.aicoder.maintenance.business.asset.info.service;


import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.jpa.GenericCrudService;

import net.aicoder.maintenance.business.asset.info.dao.AssetTypeDao;
import net.aicoder.maintenance.business.asset.info.dao.AssetTypeSpecification;
import net.aicoder.maintenance.business.asset.info.domain.AssetType;
import net.aicoder.maintenance.business.asset.info.dto.AssetTypeCondition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("assetTypeService")
public class AssetTypeService  extends GenericCrudService<AssetType, Long, AssetTypeCondition, AssetTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeService.class);

	@Transactional
	public void merge(AssetType assetType){

		if("-1".equals(assetType.getParentCode())){
			throw new BusinessException("asset", "assettype", "1000", "顶层分类不能更新");
		}
		dao.save(assetType);
	}


	@Transactional
	public void delete(Long id) {
		if (null == id) {
			this.LOGGER.warn("try delete T by empty id. Code need check");
		}

		AssetType assetType = find(id);
		if(assetType == null){
			return;
		}
		if("-1".equals(assetType.getParentCode())){
			throw new BusinessException("asset", "assettype", "1000", "顶层分类不能删除");
		}
		int childCount = dao.countAllByParentCodeAndTid(assetType.getCode(), assetType.getTid());
		if(childCount > 0){
			throw new BusinessException("ASSET", "ASSET_TYPE", "CHILD NOT EMPTY", "子节点不为空");
		}

		this.LOGGER.debug("delete t:{}", id);
		this.dao.delete(id);

	}

	public AssetType findByCode(String code, Long tid){
		return dao.findByCodeAndTid(code, tid);
	}

	public List<AssetType> findByParentCodeAndTid(String parentCode, Long tid){
		return dao.findByParentCodeAndTid(parentCode, tid);
	}

	@Override
	public Specification<AssetType> getSpecification(AssetTypeCondition condition) {
		return new AssetTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, AssetType.PROPERTY_NUM);
		return sort;
	}
}