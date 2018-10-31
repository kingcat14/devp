package com.yunkang.saas.bootstrap.application.business.authorize.controller;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;

import com.yunkang.saas.bootstrap.platform.business.platform.application.domain.App;
import com.yunkang.saas.bootstrap.platform.business.platform.application.service.AppService;
import com.yunkang.saas.bootstrap.platform.business.platform.application.vo.AppVO;
import com.yunkang.saas.bootstrap.platform.business.platform.security.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.service.TenantService;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.vo.TenantVO;
import com.yunkang.saas.common.framework.app.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${security.basic.enabled:true}")
    private boolean notInTest = false;

    @Autowired
    private AppService appService;

    @Autowired
    private TenantService tenantService;

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

//        if(account==null){
//            if(this.notInTest){
//                account = new Account();
//                account.setName("测试中");
//                account.setNickName("虚拟用户");
//                account.setTenantId(-1L);
//                return account;
//            }else {
//                throw new BusinessException("security", "account", "not login", "用户未登陆");
//            }
//        }

        return account;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    @PostMapping("/tenant")
    public TenantVO getTenant(){


        Account account =  this.getAccount();

        Long tenantId = account.getTenantId();

        Tenant tenant = tenantService.find(tenantId);
        TenantVO tenantVO = new TenantVO();
        BeanUtils.copyProperties(tenant, tenantVO);

        return tenantVO;
    }



}
