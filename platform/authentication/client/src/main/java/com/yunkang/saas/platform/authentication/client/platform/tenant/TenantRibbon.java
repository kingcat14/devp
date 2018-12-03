package com.yunkang.saas.platform.authentication.client.platform.tenant;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAddDto;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantCondition;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantEditDto;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantPageResult;
import com.yunkang.saas.platform.authentication.client.platform.tenant.result.TenantResult;
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
 * 租户客户端
 * @author icode
 */
@Service
public class TenantRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantRibbon.class);

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
     * 新增租户
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public TenantResult add(TenantAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenant";
        return restTemplate.postForObject(url, addDto, TenantResult.class);
    }
    private TenantResult addFail(TenantAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除租户
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public TenantResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenant/"+id;
        ResponseEntity<TenantResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<TenantResult>() {});
        return response.getBody();
    }
    private TenantResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新租户
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public TenantResult update(Long id, TenantEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/tenant/tenant/"+id;
        ResponseEntity<TenantResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<TenantResult>() {});
        return response.getBody();
    }

    public TenantResult updateFail(Long id, TenantEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询租户
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public TenantResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/tenant/tenant/"+id;
        return restTemplate.getForObject(url, TenantResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private TenantResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询租户列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public TenantPageResult list(PageSearchRequest<TenantCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/tenant/tenant/list";
        return restTemplate.postForObject(url, pageSearchRequest, TenantPageResult.class);
    }
    public TenantPageResult listFail(PageSearchRequest<TenantCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        TenantPageResult result = new TenantPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private TenantResult errorResult(){
        TenantResult result = new TenantResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
