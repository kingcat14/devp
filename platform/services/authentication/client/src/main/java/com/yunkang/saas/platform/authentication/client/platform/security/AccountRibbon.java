package com.yunkang.saas.platform.authentication.client.platform.security;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountAddDto;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountCondition;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountEditDto;
import com.yunkang.saas.platform.authentication.client.platform.security.result.AccountPageResult;
import com.yunkang.saas.platform.authentication.client.platform.security.result.AccountResult;
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
 * 账号客户端
 * @author icode
 */
@Service
public class AccountRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRibbon.class);

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
     * 新增账号
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AccountResult add(AccountAddDto addDto) {
        String url = "http://"+host+"/authentication/platform/security/account";
        return restTemplate.postForObject(url, addDto, AccountResult.class);
    }
    private AccountResult addFail(AccountAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除账号
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AccountResult delete(Long id) {
        String url = "http://"+host+"/authentication/platform/security/account/"+id;
        ResponseEntity<AccountResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AccountResult>() {});
        return response.getBody();
    }
    private AccountResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新账号
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AccountResult update(Long id, AccountEditDto editDto) {
        String url = "http://"+host+"/authentication/platform/security/account/"+id;
        ResponseEntity<AccountResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AccountResult>() {});
        return response.getBody();
    }

    public AccountResult updateFail(Long id, AccountEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询账号
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AccountResult get(Long id) {
        String url = "http://"+host+"/authentication/platform/security/account/"+id;
        return restTemplate.getForObject(url, AccountResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AccountResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询账号列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AccountPageResult list(PageSearchRequest<AccountCondition> pageSearchRequest) {
        String url = "http://"+host+"/authentication/platform/security/account/list";
        return restTemplate.postForObject(url, pageSearchRequest, AccountPageResult.class);
    }
    public AccountPageResult listFail(PageSearchRequest<AccountCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AccountPageResult result = new AccountPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AccountResult errorResult(){
        AccountResult result = new AccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
