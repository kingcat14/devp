package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.project.dao.ProductDao;
import net.aicoder.speedcloud.icode.business.project.dao.ProductSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.Product;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("productService")
@Slf4j
public class ProductService  extends GenericCrudService<Product, String, ProductCondition, ProductDao> {

	@Override
	public Specification<Product> getSpecification(ProductCondition condition) {
		return new ProductSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Product.PROPERTY_PRODUCT_NAME);
		return sort;
	}
}