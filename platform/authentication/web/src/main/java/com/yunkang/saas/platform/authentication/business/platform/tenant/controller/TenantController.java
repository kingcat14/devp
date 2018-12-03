package com.yunkang.saas.platform.authentication.business.platform.tenant.controller;

import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.platform.authentication.business.platform.tenant.vo.TenantVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理租户
 * @author icode
 */
@Api(description = "租户", tags = "Tenant")
@RestController
@RequestMapping(value = "/authentication/platform/tenant/tenant")
public class TenantController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

	/**
	 * 根据ID查询租户
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询租户", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "authentication.platform.tenant.tenant.get")
	public  TenantVO get(@PathVariable Long id) {

		TenantVO vo = new TenantVO();
		vo.setName("OAUTH2_TEST");
		return vo;
	}


}

