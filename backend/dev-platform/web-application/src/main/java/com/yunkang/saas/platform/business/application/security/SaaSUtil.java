package com.yunkang.saas.platform.business.application.security;

import com.yunkang.saas.common.jpa.SaaSEntity;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import com.yunkang.saas.platform.business.platform.security.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SaaSUtil {

    @Value("${sys.id:-1}")
    private Long appId;

    @Autowired(required = false)
    private SecurityUtil securityUtil;

    /**
     * 设置租户ID和应用ID
     * @param saaSEntity
     */
    public void fillSaaSEntity(SaaSEntity saaSEntity){

        Account account = securityUtil.getAccount();
        saaSEntity.setAppId(appId);
        saaSEntity.setTenantId(account.getTenantId());
    }

    public Account getAccount(){
        return securityUtil.getAccount();
    }



    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
