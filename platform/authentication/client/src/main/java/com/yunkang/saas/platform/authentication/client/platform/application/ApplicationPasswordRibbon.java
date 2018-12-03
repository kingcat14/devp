package com.yunkang.saas.platform.authentication.client.platform.application;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ApplicationPasswordAddDto;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ApplicationPasswordCondition;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ApplicationPasswordEditDto;
import com.yunkang.saas.platform.authentication.client.platform.application.result.ApplicationPasswordPageResult;
import com.yunkang.saas.platform.authentication.client.platform.application.result.ApplicationPasswordResult;
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
 * 应用密码客户端
 * @author icode
 */
@Service
public class ApplicationPasswordRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPasswordRibbon.class);

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
     * 新增应用密码
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ApplicationPasswordResult add(ApplicationPasswordAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/application/applicationpassword";
        return restTemplate.postForObject(url, addDto, ApplicationPasswordResult.class);
    }
    private ApplicationPasswordResult addFail(ApplicationPasswordAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用密码
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ApplicationPasswordResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/application/applicationpassword/"+id;
        ResponseEntity<ApplicationPasswordResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ApplicationPasswordResult>() {});
        return response.getBody();
    }
    private ApplicationPasswordResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用密码
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ApplicationPasswordResult update(Long id, ApplicationPasswordEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/application/applicationpassword/"+id;
        ResponseEntity<ApplicationPasswordResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ApplicationPasswordResult>() {});
        return response.getBody();
    }

    public ApplicationPasswordResult updateFail(Long id, ApplicationPasswordEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用密码
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ApplicationPasswordResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/application/applicationpassword/"+id;
        return restTemplate.getForObject(url, ApplicationPasswordResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ApplicationPasswordResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用密码列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ApplicationPasswordPageResult list(PageSearchRequest<ApplicationPasswordCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/application/applicationpassword/list";
        return restTemplate.postForObject(url, pageSearchRequest, ApplicationPasswordPageResult.class);
    }
    public ApplicationPasswordPageResult listFail(PageSearchRequest<ApplicationPasswordCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ApplicationPasswordPageResult result = new ApplicationPasswordPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ApplicationPasswordResult errorResult(){
        ApplicationPasswordResult result = new ApplicationPasswordResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
