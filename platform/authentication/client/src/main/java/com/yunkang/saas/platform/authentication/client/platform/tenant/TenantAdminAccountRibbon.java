package com.yunkang.saas.platform.authentication.client.platform.tenant;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAdminAccountAddDto;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAdminAccountCondition;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAdminAccountEditDto;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantAdminAccountPageResult;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantAdminAccountResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 租户管理员账号客户端
 * @author icode
 */
@Service
public class TenantAdminAccountRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantAdminAccountRibbon.class);

    private String host = "SAAS-AUTHENTICATION-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增租户管理员账号
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public TenantAdminAccountResult add(TenantAdminAccountAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenantadminaccount";
        return restTemplate.postForObject(url, addDto, TenantAdminAccountResult.class);
    }
    private TenantAdminAccountResult addFail(TenantAdminAccountAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除租户管理员账号
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public TenantAdminAccountResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenantadminaccount/"+id;
        ResponseEntity<TenantAdminAccountResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<TenantAdminAccountResult>() {});
        return response.getBody();
    }
    private TenantAdminAccountResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新租户管理员账号
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public TenantAdminAccountResult update(Long id, TenantAdminAccountEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenantadminaccount/"+id;
        ResponseEntity<TenantAdminAccountResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<TenantAdminAccountResult>() {});
        return response.getBody();
    }

    public TenantAdminAccountResult updateFail(Long id, TenantAdminAccountEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询租户管理员账号
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public TenantAdminAccountResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenantadminaccount/"+id;
        return restTemplate.getForObject(url, TenantAdminAccountResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private TenantAdminAccountResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询租户管理员账号列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public TenantAdminAccountPageResult list(PageSearchRequest<TenantAdminAccountCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/tenant/tenantadminaccount/list";
        return restTemplate.postForObject(url, pageSearchRequest, TenantAdminAccountPageResult.class);
    }
    public TenantAdminAccountPageResult listFail(PageSearchRequest<TenantAdminAccountCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        TenantAdminAccountPageResult result = new TenantAdminAccountPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private TenantAdminAccountResult errorResult(){
        TenantAdminAccountResult result = new TenantAdminAccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
