package net.aicoder.speedcloud.icode.business.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.icode.business.project.domain.Product;
import net.aicoder.speedcloud.icode.business.project.dto.ProductAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductEditDto;
import net.aicoder.speedcloud.icode.business.project.service.ProductService;
import net.aicoder.speedcloud.icode.business.project.valid.ProductValidator;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理产品
 * @author icode
 */
@Api(description = "产品", tags = "Product")
@RestController
@RequestMapping(value = "/icode/project/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


	@Autowired
	private ProductService productService;



	@Autowired
	private ProductValidator productValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(productValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品
	 * @param productAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.project.product.add")
	public ProductVO add(@RequestBody @Valid ProductAddDto productAddDto){
		Product product = new Product();
		BeanUtils.copyProperties(productAddDto, product);

		productService.add(product);

		return  initViewProperty(product);
	}

	/**
	 * 删除产品,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.project.product.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete product :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			productService.delete(id);
		}

	}

	/**
	 * 更新产品
	 * @param productEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.product.update")
	public	ProductVO update(@RequestBody @Valid ProductEditDto productEditDto, @PathVariable String id){
		Product product = new Product();
		BeanUtils.copyProperties(productEditDto, product);
		product.setId(id);
		productService.merge(product);

		ProductVO vo = initViewProperty(product);
		return  vo;
	}

	/**
	 * 根据ID查询产品
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.product.get")
	public  ProductVO get(@PathVariable String id) {

		Product product = productService.find(id);

		ProductVO vo = initViewProperty(product);
		return vo;
	}

	/**
	 * 查询产品列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.project.product.list")
	public PageContent<ProductVO> list(@RequestBody PageSearchRequest<ProductCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Product> page = productService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ProductVO> voList = new ArrayList<>();
		for(Product product : page.getContent()){
			voList.add(initViewProperty(product));
		}

		PageContent<ProductVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ProductCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ProductCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ProductVO> content = this.list(pageSearchRequest);

        List<ProductVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ProductVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();


        String title = new String("产品");
        String fileName = new String(("产品_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ProductVO initViewProperty(Product product){

	    ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);


	    //初始化其他对象
        return vo;

	}


}

