package com.yunkang.saas.platform.authentication.client.platform.tenant;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantTypeAddDto;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantTypeCondition;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantTypeEditDto;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantTypePageResult;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantTypeResult;
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
 * 租户类型客户端
 * @author icode
 */
@Service
public class TenantTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantTypeRibbon.class);

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
     * 新增租户类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public TenantTypeResult add(TenantTypeAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenanttype";
        return restTemplate.postForObject(url, addDto, TenantTypeResult.class);
    }
    private TenantTypeResult addFail(TenantTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除租户类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public TenantTypeResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenanttype/"+id;
        ResponseEntity<TenantTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<TenantTypeResult>() {});
        return response.getBody();
    }
    private TenantTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新租户类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public TenantTypeResult update(Long id, TenantTypeEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenanttype/"+id;
        ResponseEntity<TenantTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<TenantTypeResult>() {});
        return response.getBody();
    }

    public TenantTypeResult updateFail(Long id, TenantTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询租户类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public TenantTypeResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenanttype/"+id;
        return restTemplate.getForObject(url, TenantTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private TenantTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询租户类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public TenantTypePageResult list(PageSearchRequest<TenantTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/tenant/tenanttype/list";
        return restTemplate.postForObject(url, pageSearchRequest, TenantTypePageResult.class);
    }
    public TenantTypePageResult listFail(PageSearchRequest<TenantTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        TenantTypePageResult result = new TenantTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private TenantTypeResult errorResult(){
        TenantTypeResult result = new TenantTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
