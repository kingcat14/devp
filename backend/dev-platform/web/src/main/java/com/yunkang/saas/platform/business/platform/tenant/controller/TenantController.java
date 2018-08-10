package com.yunkang.saas.platform.business.platform.tenant.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.yunkang.saas.platform.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantCondition;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantAddDto;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantEditDto;
import com.yunkang.saas.platform.business.platform.tenant.service.TenantService;
import com.yunkang.saas.platform.business.platform.tenant.valid.TenantValidator;
import com.yunkang.saas.platform.business.platform.tenant.vo.TenantVO;
import com.yunkang.saas.platform.business.platform.tenant.domain.TenantType;
import com.yunkang.saas.platform.business.platform.tenant.service.TenantTypeService;
import com.yunkang.saas.platform.business.platform.tenant.vo.TenantTypeVO;

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
 * 管理租户
 * @author icode
 */
@Api(description = "租户", tags = "Tenant")
@RestController
@RequestMapping(value = "/platform/tenant/tenant")
public class TenantController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);


	@Autowired
	private TenantService tenantService;

	@Autowired
	private TenantTypeService tenantTypeService;

	@Autowired
	private TenantValidator tenantValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(tenantValidator);
	}

	/**
	 * 新增租户
	 * @param tenantAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增租户", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TenantVO add(@RequestBody @Valid TenantAddDto tenantAddDto){
		Tenant tenant = new Tenant();
		BeanUtils.copyProperties(tenantAddDto, tenant);

		tenantService.add(tenant);

		return  initViewProperty(tenant);
	}

	/**
	 * 删除租户,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除租户", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tenant :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			tenantService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新租户
	 * @param tenantEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产租户(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	TenantVO update(@RequestBody @Valid TenantEditDto tenantEditDto, @PathVariable Long id){
		Tenant tenant = new Tenant();
		BeanUtils.copyProperties(tenantEditDto, tenant);
		tenant.setId(id);
		tenantService.merge(tenant);

		TenantVO vo = initViewProperty(tenant);
		return  vo;
	}

	/**
	 * 根据ID查询租户
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询租户", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  TenantVO get(@PathVariable Long id) {

		Tenant tenant = tenantService.find(id);

		TenantVO vo = initViewProperty(tenant);
		return vo;
	}

	/**
	 * 查询租户列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询租户列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<TenantVO> list(@RequestBody PageSearchRequest<TenantCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<Tenant> page = tenantService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<TenantVO> voList = new ArrayList<>();
		for(Tenant tenant : page.getContent()){
			voList.add(initViewProperty(tenant));
		}

		PageContent<TenantVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TenantVO initViewProperty(Tenant tenant){
	    TenantVO vo = new TenantVO();

        BeanUtils.copyProperties(tenant, vo);

	    //初始化其他对象
	    initTenantTypePropertyGroup(vo, tenant);
        return vo;
	}


	private void initTenantTypePropertyGroup(TenantVO tenantVO, Tenant tenant){
	
		TenantType tenantType = tenantTypeService.find(tenant.getTenantType());
		if(tenantType == null){
			return;
		}
		TenantTypeVO tenantTypeVO = new TenantTypeVO();
		BeanUtils.copyProperties(tenantType, tenantTypeVO);

		tenantVO.setTenantTypeVO(tenantTypeVO);

	}


}
