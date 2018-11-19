package net.aicoder.speedcloud.client.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelEditDto;
import net.aicoder.speedcloud.client.config.result.EnvConfigLevelPageResult;
import net.aicoder.speedcloud.client.config.result.EnvConfigLevelResult;
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
 * 环境级别客户端
 * @author icode
 */
@Service
public class EnvConfigLevelRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvConfigLevelRibbon.class);

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
     * 新增环境级别
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EnvConfigLevelResult add(EnvConfigLevelAddDto addDto) {
        String url = "http://"+host+"/speedcloud/config/envconfiglevel";
        return restTemplate.postForObject(url, addDto, EnvConfigLevelResult.class);
    }
    private EnvConfigLevelResult addFail(EnvConfigLevelAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除环境级别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EnvConfigLevelResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/config/envconfiglevel/"+id;
        ResponseEntity<EnvConfigLevelResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EnvConfigLevelResult>() {});
        return response.getBody();
    }
    private EnvConfigLevelResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新环境级别
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EnvConfigLevelResult update(Long id, EnvConfigLevelEditDto editDto) {
        String url = "http://"+host+"/speedcloud/config/envconfiglevel/"+id;
        ResponseEntity<EnvConfigLevelResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EnvConfigLevelResult>() {});
        return response.getBody();
    }

    public EnvConfigLevelResult updateFail(Long id, EnvConfigLevelEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询环境级别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EnvConfigLevelResult get(Long id) {
        String url = "http://"+host+"/speedcloud/config/envconfiglevel/"+id;
        return restTemplate.getForObject(url, EnvConfigLevelResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EnvConfigLevelResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询环境级别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EnvConfigLevelPageResult list(PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/config/envconfiglevel/list";
        return restTemplate.postForObject(url, pageSearchRequest, EnvConfigLevelPageResult.class);
    }
    public EnvConfigLevelPageResult listFail(PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EnvConfigLevelPageResult result = new EnvConfigLevelPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EnvConfigLevelResult errorResult(){
        EnvConfigLevelResult result = new EnvConfigLevelResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
