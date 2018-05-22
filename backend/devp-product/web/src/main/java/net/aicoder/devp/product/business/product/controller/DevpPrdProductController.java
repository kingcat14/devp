package net.aicoder.devp.product.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import net.aicoder.devp.product.business.product.domain.DevpPrdProduct;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.product.business.product.service.DevpPrdProductService;
import net.aicoder.devp.product.business.product.valid.DevpPrdProductValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdProductVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理产品定义
 * @author icode
 */
@RestController
@RequestMapping(value = "/product/devpPrdProduct")
public class DevpPrdProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductController.class);


	@Autowired
	private DevpPrdProductService devpPrdProductService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdProductValidator());
	}

	/**
	 * 新增产品定义
	 * @param devpPrdProductAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdProductVO add(@RequestBody @Valid DevpPrdProductAddDto devpPrdProductAddDto){
		DevpPrdProduct devpPrdProduct = new DevpPrdProduct();
		BeanUtils.copyProperties(devpPrdProductAddDto, devpPrdProduct);

		devpPrdProductService.add(devpPrdProduct);

		return  initViewProperty(devpPrdProduct);
	}

	/**
	 * 删除产品定义,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdProduct :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdProductService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品定义
	 * @param devpPrdProductEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	DevpPrdProductVO update(@RequestBody @Valid DevpPrdProductEditDto devpPrdProductEditDto, @PathVariable Long id){
		DevpPrdProduct devpPrdProduct = new DevpPrdProduct();
		BeanUtils.copyProperties(devpPrdProductEditDto, devpPrdProduct);
		devpPrdProduct.setId(id);
		devpPrdProductService.merge(devpPrdProduct);

		DevpPrdProductVO vo = initViewProperty(devpPrdProduct);
		return  vo;
	}

	/**
	 * 根据ID查询产品定义
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  DevpPrdProductVO get(@PathVariable Long id) {

		DevpPrdProduct devpPrdProduct = devpPrdProductService.find(id);

		DevpPrdProductVO vo = initViewProperty(devpPrdProduct);
		return vo;
	}

	/**
	 * 查询产品定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DevpPrdProductVO> list(@RequestBody PageSearchRequest<DevpPrdProductCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpPrdProduct> page = devpPrdProductService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdProductVO> voList = new ArrayList<>();
		for(DevpPrdProduct devpPrdProduct : page.getContent()){
			voList.add(initViewProperty(devpPrdProduct));
		}

		PageContent<DevpPrdProductVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpPrdProductVO initViewProperty(DevpPrdProduct devpPrdProduct){
	    DevpPrdProductVO vo = new DevpPrdProductVO();

        BeanUtils.copyProperties(devpPrdProduct, vo);

	    //初始化其他对象
        return vo;
	}




}
