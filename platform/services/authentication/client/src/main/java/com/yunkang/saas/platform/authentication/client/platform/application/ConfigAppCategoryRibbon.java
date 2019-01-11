package com.yunkang.saas.platform.authentication.client.platform.application;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ConfigAppCategoryAddDto;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ConfigAppCategoryCondition;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ConfigAppCategoryEditDto;
import com.yunkang.saas.platform.authentication.client.platform.application.result.ConfigAppCategoryPageResult;
import com.yunkang.saas.platform.authentication.client.platform.application.result.ConfigAppCategoryResult;
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
 * 应用类别客户端
 * @author icode
 */
@Service
public class ConfigAppCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAppCategoryRibbon.class);

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
     * 新增应用类别
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ConfigAppCategoryResult add(ConfigAppCategoryAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/application/configappcategory";
        return restTemplate.postForObject(url, addDto, ConfigAppCategoryResult.class);
    }
    private ConfigAppCategoryResult addFail(ConfigAppCategoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用类别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ConfigAppCategoryResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/application/configappcategory/"+id;
        ResponseEntity<ConfigAppCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ConfigAppCategoryResult>() {});
        return response.getBody();
    }
    private ConfigAppCategoryResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用类别
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ConfigAppCategoryResult update(Long id, ConfigAppCategoryEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/application/configappcategory/"+id;
        ResponseEntity<ConfigAppCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ConfigAppCategoryResult>() {});
        return response.getBody();
    }

    public ConfigAppCategoryResult updateFail(Long id, ConfigAppCategoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用类别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ConfigAppCategoryResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/application/configappcategory/"+id;
        return restTemplate.getForObject(url, ConfigAppCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ConfigAppCategoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ConfigAppCategoryPageResult list(PageSearchRequest<ConfigAppCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/application/configappcategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, ConfigAppCategoryPageResult.class);
    }
    public ConfigAppCategoryPageResult listFail(PageSearchRequest<ConfigAppCategoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ConfigAppCategoryPageResult result = new ConfigAppCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ConfigAppCategoryResult errorResult(){
        ConfigAppCategoryResult result = new ConfigAppCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
