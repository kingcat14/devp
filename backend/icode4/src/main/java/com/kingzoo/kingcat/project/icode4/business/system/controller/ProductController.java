package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ProductAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import com.kingzoo.kingcat.project.icode4.business.system.service.ProductService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.ProductValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ProductCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理Product
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


	@Autowired
	private ProductService productService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ProductValidator());
	}

	/**
	 * 新增Product
	 * @param productAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProductVO add(@RequestBody @Valid ProductAddRequest productAddRequest){
		Product product = new Product();
		BeanUtils.copyProperties(productAddRequest, product);

		productService.add(product);

		return  initViewProperty(product);
	}

	/**
	 * 删除Product,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete product :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			productService.delete(id);
		}

	}

	/**
	 * 更新Product
	 * @param product
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public ProductVO update(@RequestBody @Valid Product product, @PathVariable String id){
		product.setId(id);
		productService.merge(product);

		ProductVO vo = initViewProperty(product);
		return  vo;
	}

	/**
	 * 根据ID查询Product
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ProductVO get(@PathVariable String id) {

		Product product = productService.find(id);

		ProductVO vo = initViewProperty(product);
		return vo;
	}

	/**
	 * 查询Product列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ProductVO> list(@RequestBody PageSearchRequest<ProductCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<Product> page = productService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ProductVO> voList = new ArrayList<>();
		for(Product product : page.getContent()){
			voList.add(initViewProperty(product));
		}

		PageContent<ProductVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ProductVO initViewProperty(Product product){
	    ProductVO vo = new ProductVO();

        BeanUtils.copyProperties(product, vo);

	    //初始化其他对象
        return vo;
	}




}
