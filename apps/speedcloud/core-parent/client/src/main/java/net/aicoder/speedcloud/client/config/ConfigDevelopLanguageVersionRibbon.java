package net.aicoder.speedcloud.client.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageVersionPageResult;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageVersionResult;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionEditDto;
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
 * 开发语言版本客户端
 * @author icode
 */
@Service
public class ConfigDevelopLanguageVersionRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageVersionRibbon.class);

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
     * 新增开发语言版本
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ConfigDevelopLanguageVersionResult add(ConfigDevelopLanguageVersionAddDto addDto) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguageversion";
        return restTemplate.postForObject(url, addDto, ConfigDevelopLanguageVersionResult.class);
    }
    private ConfigDevelopLanguageVersionResult addFail(ConfigDevelopLanguageVersionAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除开发语言版本
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ConfigDevelopLanguageVersionResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguageversion/"+id;
        ResponseEntity<ConfigDevelopLanguageVersionResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ConfigDevelopLanguageVersionResult>() {});
        return response.getBody();
    }
    private ConfigDevelopLanguageVersionResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新开发语言版本
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ConfigDevelopLanguageVersionResult update(Long id, ConfigDevelopLanguageVersionEditDto editDto) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguageversion/"+id;
        ResponseEntity<ConfigDevelopLanguageVersionResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ConfigDevelopLanguageVersionResult>() {});
        return response.getBody();
    }

    public ConfigDevelopLanguageVersionResult updateFail(Long id, ConfigDevelopLanguageVersionEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询开发语言版本
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ConfigDevelopLanguageVersionResult get(Long id) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguageversion/"+id;
        return restTemplate.getForObject(url, ConfigDevelopLanguageVersionResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ConfigDevelopLanguageVersionResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询开发语言版本列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ConfigDevelopLanguageVersionPageResult list(PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguageversion/list";
        return restTemplate.postForObject(url, pageSearchRequest, ConfigDevelopLanguageVersionPageResult.class);
    }
    public ConfigDevelopLanguageVersionPageResult listFail(PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ConfigDevelopLanguageVersionPageResult result = new ConfigDevelopLanguageVersionPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ConfigDevelopLanguageVersionResult errorResult(){
        ConfigDevelopLanguageVersionResult result = new ConfigDevelopLanguageVersionResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
