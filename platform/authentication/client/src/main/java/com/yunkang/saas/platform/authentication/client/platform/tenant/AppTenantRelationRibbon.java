package com.yunkang.saas.platform.authentication.client.platform.tenant;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.AppTenantRelationAddDto;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.AppTenantRelationCondition;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.AppTenantRelationEditDto;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.AppTenantRelationPageResult;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.AppTenantRelationResult;
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
 * 租户开通的应用客户端
 * @author icode
 */
@Service
public class AppTenantRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppTenantRelationRibbon.class);

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
     * 新增租户开通的应用
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppTenantRelationResult add(AppTenantRelationAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/tenant/apptenantrelation";
        return restTemplate.postForObject(url, addDto, AppTenantRelationResult.class);
    }
    private AppTenantRelationResult addFail(AppTenantRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除租户开通的应用
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppTenantRelationResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/apptenantrelation/"+id;
        ResponseEntity<AppTenantRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppTenantRelationResult>() {});
        return response.getBody();
    }
    private AppTenantRelationResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新租户开通的应用
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppTenantRelationResult update(Long id, AppTenantRelationEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/tenant/apptenantrelation/"+id;
        ResponseEntity<AppTenantRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppTenantRelationResult>() {});
        return response.getBody();
    }

    public AppTenantRelationResult updateFail(Long id, AppTenantRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询租户开通的应用
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppTenantRelationResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/apptenantrelation/"+id;
        return restTemplate.getForObject(url, AppTenantRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppTenantRelationResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询租户开通的应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppTenantRelationPageResult list(PageSearchRequest<AppTenantRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/tenant/apptenantrelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppTenantRelationPageResult.class);
    }
    public AppTenantRelationPageResult listFail(PageSearchRequest<AppTenantRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AppTenantRelationPageResult result = new AppTenantRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AppTenantRelationResult errorResult(){
        AppTenantRelationResult result = new AppTenantRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
