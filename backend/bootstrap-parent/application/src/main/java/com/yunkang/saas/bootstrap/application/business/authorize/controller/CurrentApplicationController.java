package com.yunkang.saas.bootstrap.application.business.authorize.controller;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;

import com.yunkang.saas.bootstrap.platform.business.application.domain.App;
import com.yunkang.saas.bootstrap.platform.business.application.service.AppService;
import com.yunkang.saas.bootstrap.platform.business.application.vo.AppVO;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.platform.business.tenant.service.TenantService;
import com.yunkang.saas.bootstrap.platform.business.tenant.vo.TenantVO;
import com.yunkang.saas.common.framework.app.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 得到当前应用
 */
@RestController
@RequestMapping(value = "/current")
public class CurrentApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentApplicationController.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private SecurityUtil securityUtil;

    @Value("${management.security.enabled:true}")
    private boolean notInTest = false;

    @Autowired
    private AppService appService;

    @Autowired
    private TenantService tenantService;

    /**
     * 获取当前应用
     * @return
     */
    @PostMapping("/app")
    public AppVO getApp(){
        String appCode = applicationProperties.getCode()+"";
        App app = appService.findByCode(appCode);
        AppVO appVO = new AppVO();
        BeanUtils.copyProperties(app, appVO);
        return appVO;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @PostMapping("/account")
    public Account getAccount(){

        Account account =  securityUtil.getAccount();

        return account;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @PostMapping("/tenant")
    public TenantVO getTenant(){


        Account account =  securityUtil.getAccount();

        Long tenantId = account.getTid();

        Tenant tenant = tenantService.find(tenantId);
        TenantVO tenantVO = new TenantVO();
        BeanUtils.copyProperties(tenant, tenantVO);

        return tenantVO;
    }




}
