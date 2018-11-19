package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigEditDto;
import net.aicoder.speedcloud.client.app.result.SecurityConfigPageResult;
import net.aicoder.speedcloud.client.app.result.SecurityConfigResult;
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
 * 应用私密配置客户端
 * @author icode
 */
@Service
public class SecurityConfigRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfigRibbon.class);

    private String host = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增应用私密配置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public SecurityConfigResult add(SecurityConfigAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/securityconfig";
        return restTemplate.postForObject(url, addDto, SecurityConfigResult.class);
    }
    private SecurityConfigResult addFail(SecurityConfigAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用私密配置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public SecurityConfigResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/securityconfig/"+id;
        ResponseEntity<SecurityConfigResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<SecurityConfigResult>() {});
        return response.getBody();
    }
    private SecurityConfigResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用私密配置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public SecurityConfigResult update(Long id, SecurityConfigEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/securityconfig/"+id;
        ResponseEntity<SecurityConfigResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<SecurityConfigResult>() {});
        return response.getBody();
    }

    public SecurityConfigResult updateFail(Long id, SecurityConfigEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用私密配置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public SecurityConfigResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/securityconfig/"+id;
        return restTemplate.getForObject(url, SecurityConfigResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private SecurityConfigResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用私密配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public SecurityConfigPageResult list(PageSearchRequest<SecurityConfigCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/securityconfig/list";
        return restTemplate.postForObject(url, pageSearchRequest, SecurityConfigPageResult.class);
    }
    public SecurityConfigPageResult listFail(PageSearchRequest<SecurityConfigCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SecurityConfigPageResult result = new SecurityConfigPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private SecurityConfigResult errorResult(){
        SecurityConfigResult result = new SecurityConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
