package com.yunkang.saas.bootstrap.business.platform.tenant.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.yunkang.saas.bootstrap.business.platform.tenant.domain.TenantType;
import com.yunkang.saas.bootstrap.business.platform.tenant.dto.TenantTypeCondition;
import com.yunkang.saas.bootstrap.business.platform.tenant.dto.TenantTypeAddDto;
import com.yunkang.saas.bootstrap.business.platform.tenant.dto.TenantTypeEditDto;
import com.yunkang.saas.bootstrap.business.platform.tenant.service.TenantTypeService;
import com.yunkang.saas.bootstrap.business.platform.tenant.valid.TenantTypeValidator;
import com.yunkang.saas.bootstrap.business.platform.tenant.vo.TenantTypeVO;

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
 * 管理租户类型
 * @author icode
 */
@Api(description = "租户类型", tags = "TenantType")
@RestController
@RequestMapping(value = "/platform/tenant/tenantType")
public class TenantTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantTypeController.class);


	@Autowired
	private TenantTypeService tenantTypeService;


	@Autowired
	private TenantTypeValidator tenantTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(tenantTypeValidator);
	}

	/**
	 * 新增租户类型
	 * @param tenantTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增租户类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TenantTypeVO add(@RequestBody @Valid TenantTypeAddDto tenantTypeAddDto){
		TenantType tenantType = new TenantType();
		BeanUtils.copyProperties(tenantTypeAddDto, tenantType);

		tenantTypeService.add(tenantType);

		return  initViewProperty(tenantType);
	}

	/**
	 * 删除租户类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除租户类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tenantType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			tenantTypeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新租户类型
	 * @param tenantTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产租户类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	TenantTypeVO update(@RequestBody @Valid TenantTypeEditDto tenantTypeEditDto, @PathVariable Long id){
		TenantType tenantType = new TenantType();
		BeanUtils.copyProperties(tenantTypeEditDto, tenantType);
		tenantType.setId(id);
		tenantTypeService.merge(tenantType);

		TenantTypeVO vo = initViewProperty(tenantType);
		return  vo;
	}

	/**
	 * 根据ID查询租户类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询租户类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  TenantTypeVO get(@PathVariable Long id) {

		TenantType tenantType = tenantTypeService.find(id);

		TenantTypeVO vo = initViewProperty(tenantType);
		return vo;
	}

	/**
	 * 查询租户类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询租户类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<TenantTypeVO> list(@RequestBody PageSearchRequest<TenantTypeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<TenantType> page = tenantTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<TenantTypeVO> voList = new ArrayList<>();
		for(TenantType tenantType : page.getContent()){
			voList.add(initViewProperty(tenantType));
		}

		PageContent<TenantTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TenantTypeVO initViewProperty(TenantType tenantType){
	    TenantTypeVO vo = new TenantTypeVO();

        BeanUtils.copyProperties(tenantType, vo);

	    //初始化其他对象
        return vo;
	}


}
