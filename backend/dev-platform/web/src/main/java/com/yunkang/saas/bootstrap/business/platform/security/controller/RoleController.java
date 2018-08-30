package com.yunkang.saas.bootstrap.business.platform.security.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.bootstrap.business.platform.application.domain.App;
import com.yunkang.saas.bootstrap.business.platform.application.service.AppService;
import com.yunkang.saas.bootstrap.business.platform.application.vo.AppVO;
import com.yunkang.saas.bootstrap.business.platform.security.domain.Role;
import com.yunkang.saas.bootstrap.business.platform.security.dto.RoleAddDto;
import com.yunkang.saas.bootstrap.business.platform.security.dto.RoleCondition;
import com.yunkang.saas.bootstrap.business.platform.security.dto.RoleEditDto;
import com.yunkang.saas.bootstrap.business.platform.security.service.RoleService;
import com.yunkang.saas.bootstrap.business.platform.security.valid.RoleValidator;
import com.yunkang.saas.bootstrap.business.platform.security.vo.RoleVO;
import com.yunkang.saas.bootstrap.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.business.platform.tenant.service.TenantService;
import com.yunkang.saas.bootstrap.business.platform.tenant.vo.TenantVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理角色
 * @author icode
 */
@RestController
@RequestMapping(value = "/platform/security/role")
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);


	@Autowired
	private RoleService roleService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private AppService appService;



	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new RoleValidator());
	}

	/**
	 * 新增角色
	 * @param roleAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public RoleVO add(@RequestBody @Valid RoleAddDto roleAddDto){
		Role role = new Role();
		BeanUtils.copyProperties(roleAddDto, role);

		roleService.add(role);

		return  initViewProperty(role);
	}

	/**
	 * 删除角色,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete role :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			roleService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新角色
	 * @param roleEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	RoleVO update(@RequestBody @Valid RoleEditDto roleEditDto, @PathVariable Long id){
		Role role = new Role();
		BeanUtils.copyProperties(roleEditDto, role);
		role.setId(id);
		roleService.merge(role);

		RoleVO vo = initViewProperty(role);
		return  vo;
	}

	/**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  RoleVO get(@PathVariable Long id) {

		Role role = roleService.find(id);

		RoleVO vo = initViewProperty(role);
		return vo;
	}

	/**
	 * 查询角色列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<RoleVO> list(@RequestBody PageSearchRequest<RoleCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<Role> page = roleService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<RoleVO> voList = new ArrayList<>();
		for(Role role : page.getContent()){
			voList.add(initViewProperty(role));
		}

		PageContent<RoleVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private RoleVO initViewProperty(Role role){
	    RoleVO vo = new RoleVO();

        BeanUtils.copyProperties(role, vo);

		Tenant tenant = tenantService.find(role.getTenantId());
		if(tenant != null){
			TenantVO tenantVO = new TenantVO();
			BeanUtils.copyProperties(tenant, tenantVO);
			vo.setTenantVO(tenantVO);
		}

		App app = appService.findByCode(role.getAppCode());
		if(app != null){
			AppVO appVO = new AppVO();
			BeanUtils.copyProperties(app, appVO);
			vo.setAppVO(appVO);
		}

	    //初始化其他对象
        return vo;
	}






}
