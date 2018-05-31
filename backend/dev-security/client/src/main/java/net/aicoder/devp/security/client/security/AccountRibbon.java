package net.aicoder.devp.security.client.security;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.security.business.security.dto.AccountAddDto;
import net.aicoder.devp.security.business.security.dto.AccountCondition;
import net.aicoder.devp.security.business.security.dto.AccountEditDto;
import net.aicoder.devp.security.client.security.result.AccountPageResult;
import net.aicoder.devp.security.client.security.result.AccountResult;
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

    private String host = "SECURITY";

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
        String url = "http://"+host+"/security/account";
        return restTemplate.postForObject(url, addDto, AccountResult.class);
    }
    private AccountResult addFail(AccountAddDto addDto) {
        AccountResult result = new AccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除账号
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AccountResult delete(Long id) {
        String url = "http://"+host+"/security/account/"+id;
        ResponseEntity<AccountResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AccountResult>() {});
        return response.getBody();
    }
    private AccountResult deleteFail(Long id) {
        AccountResult result = new AccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新账号
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AccountResult update(Long id, AccountEditDto editDto) {
        String url = "http://"+host+"/security/account/"+id;
        ResponseEntity<AccountResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AccountResult>() {});
        return response.getBody();
    }

    public AccountResult updateFail(Long id, AccountEditDto updateRequest) {
        AccountResult result = new AccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询账号
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AccountResult get(Long id) {
        String url = "http://"+host+"/security/account/"+id;
        return restTemplate.getForObject(url, AccountResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AccountResult getFail(Long id) {
        AccountResult result = new AccountResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询账号列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AccountPageResult list(PageSearchRequest<AccountCondition> pageSearchRequest) {
        String url = "http://"+host+"/security/account/list";
        return restTemplate.postForObject(url, pageSearchRequest, AccountPageResult.class);
    }
    public AccountPageResult listFail(PageSearchRequest<AccountCondition> pageSearchRequest) {
        AccountPageResult result = new AccountPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
