package com.yunkang.saas.bootstrap.application.business.security;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import com.yunkang.saas.bootstrap.business.platform.security.domain.Account;
import com.yunkang.saas.common.jpa.SaaSEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SaaSUtil {

    @Value("${application.code:-1}")
    private String appCode;

    @Autowired(required = false)
    private SecurityUtil securityUtil;


    @Autowired(required = true)
    private ApplicationProperties applicationProperties;

    /**
     * 设置租户ID和应用ID
     * @param saaSEntity
     */
    public void fillSaaSEntity(SaaSEntity saaSEntity){
        Account account = securityUtil.getAccount();
        saaSEntity.setTenantId(account.getTenantId());
    }

    public Account getAccount(){
        return securityUtil.getAccount();
    }

    public String getAppCode() {
        return applicationProperties.getCode();
    }

}
