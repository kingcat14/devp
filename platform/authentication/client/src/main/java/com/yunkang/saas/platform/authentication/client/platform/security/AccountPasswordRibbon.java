package com.yunkang.saas.platform.authentication.client.platform.security;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountPasswordAddDto;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountPasswordCondition;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountPasswordEditDto;
import com.yunkang.saas.platform.authentication.client.platform.security.result.AccountPasswordPageResult;
import com.yunkang.saas.platform.authentication.client.platform.security.result.AccountPasswordResult;
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
 * 账号密码客户端
 * @author icode
 */
@Service
public class AccountPasswordRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountPasswordRibbon.class);

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
     * 新增账号密码
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AccountPasswordResult add(AccountPasswordAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/security/accountpassword";
        return restTemplate.postForObject(url, addDto, AccountPasswordResult.class);
    }
    private AccountPasswordResult addFail(AccountPasswordAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除账号密码
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AccountPasswordResult delete(String id) {
        String url = "http://"+host+"/authentication/platform/security/accountpassword/"+id;
        ResponseEntity<AccountPasswordResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AccountPasswordResult>() {});
        return response.getBody();
    }
    private AccountPasswordResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新账号密码
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AccountPasswordResult update(String id, AccountPasswordEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/security/accountpassword/"+id;
        ResponseEntity<AccountPasswordResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AccountPasswordResult>() {});
        return response.getBody();
    }

    public AccountPasswordResult updateFail(String id, AccountPasswordEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询账号密码
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AccountPasswordResult get(String id) {
        String url = "http://"+host+"/authentication/platform/security/accountpassword/"+id;
        return restTemplate.getForObject(url, AccountPasswordResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AccountPasswordResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询账号密码列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AccountPasswordPageResult list(PageSearchRequest<AccountPasswordCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/security/accountpassword/list";
        return restTemplate.postForObject(url, pageSearchRequest, AccountPasswordPageResult.class);
    }
    public AccountPasswordPageResult listFail(PageSearchRequest<AccountPasswordCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AccountPasswordPageResult result = new AccountPasswordPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AccountPasswordResult errorResult(){
        AccountPasswordResult result = new AccountPasswordResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
