package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.ProductDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ProductCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("productService")
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);

	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;

	@Transactional
	public void add(Product product){
		product.setId(IDGenerator.get());
		productDao.insert(product);
	}

	@Transactional
	public void add(List<Product> productList){
		for(Product product:productList){
			this.add(product);
		}
	}

	@Transactional
	public void delete(Product product){
	    LOGGER.debug("delete product:{}", product);
		productDao.delete(product.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete Product by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete product:{}", id);
		productDao.delete(id);
	}

	@Transactional
	public void delete(List<Product> products){
		for(Product product: products){
			delete(product.getId());
		}
	}

	@Transactional
	public void merge(Product product){
		productDao.save(product);
	}

	@Transactional
	public void merge(List<Product> productList){
		for(Product product:productList){
			this.merge(product);
		}
	}

	@Transactional(readOnly=true)
	public Product find(String id){
	    Product product = null;
		if(StringUtils.isNotEmpty(id)) {
            product = productDao.findOne(id);
        }

		return product;
	}

	@Transactional(readOnly=true)
	public Product findOne(ProductCondition productCondition){

		List<Product> productList = productDao.findAll(productCondition);

		Product result = null;
		if(CollectionUtils.isNotEmpty(productList)){
			result = productList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<Product> findAll(ProductCondition productCondition){

		List<Product> productList = productDao.findAll(productCondition, getDefaultSort());

		return productList;
	}

	@Transactional(readOnly=true)
	public Page<Product> find(Pageable pageable){
		Page<Product> productList = productDao.findAll(pageable, null, getDefaultSort());
		return productList;
	}

	@Transactional(readOnly=true)
	public Page<Product> find(Pageable pageable, ProductCondition productCondition){
		Page<Product> productList = productDao.findAll(pageable, productCondition, getDefaultSort());
		return productList;
	}

	@Transactional(readOnly=true)
	public long count(ProductCondition productCondition){
		long count = productDao.count(productCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Product.PROPERTY_PRODUCT_NAME);
		return sort;
	}





}