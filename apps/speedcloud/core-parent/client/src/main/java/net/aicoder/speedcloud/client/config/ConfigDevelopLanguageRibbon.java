package net.aicoder.speedcloud.client.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguagePageResult;
import net.aicoder.speedcloud.client.config.result.ConfigDevelopLanguageResult;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageEditDto;
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
 * 开发语言客户端
 * @author icode
 */
@Service
public class ConfigDevelopLanguageRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageRibbon.class);

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
     * 新增开发语言
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ConfigDevelopLanguageResult add(ConfigDevelopLanguageAddDto addDto) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguage";
        return restTemplate.postForObject(url, addDto, ConfigDevelopLanguageResult.class);
    }
    private ConfigDevelopLanguageResult addFail(ConfigDevelopLanguageAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除开发语言
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ConfigDevelopLanguageResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguage/"+id;
        ResponseEntity<ConfigDevelopLanguageResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ConfigDevelopLanguageResult>() {});
        return response.getBody();
    }
    private ConfigDevelopLanguageResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新开发语言
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ConfigDevelopLanguageResult update(Long id, ConfigDevelopLanguageEditDto editDto) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguage/"+id;
        ResponseEntity<ConfigDevelopLanguageResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ConfigDevelopLanguageResult>() {});
        return response.getBody();
    }

    public ConfigDevelopLanguageResult updateFail(Long id, ConfigDevelopLanguageEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询开发语言
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ConfigDevelopLanguageResult get(Long id) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguage/"+id;
        return restTemplate.getForObject(url, ConfigDevelopLanguageResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ConfigDevelopLanguageResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询开发语言列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ConfigDevelopLanguagePageResult list(PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/config/configdeveloplanguage/list";
        return restTemplate.postForObject(url, pageSearchRequest, ConfigDevelopLanguagePageResult.class);
    }
    public ConfigDevelopLanguagePageResult listFail(PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ConfigDevelopLanguagePageResult result = new ConfigDevelopLanguagePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ConfigDevelopLanguageResult errorResult(){
        ConfigDevelopLanguageResult result = new ConfigDevelopLanguageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
