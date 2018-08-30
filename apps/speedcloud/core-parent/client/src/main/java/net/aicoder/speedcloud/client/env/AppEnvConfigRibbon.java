package net.aicoder.speedcloud.client.env;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.env.result.AppEnvConfigPageResult;
import net.aicoder.speedcloud.client.env.result.AppEnvConfigResult;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
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
 * 应用环境客户端
 * @author icode
 */
@Service
public class AppEnvConfigRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigRibbon.class);

    private String host = "SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增应用环境
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppEnvConfigResult add(AppEnvConfigAddDto addDto) {
        String url = "http://"+host+"/speedcloud/env/appenvconfig";
        return restTemplate.postForObject(url, addDto, AppEnvConfigResult.class);
    }
    private AppEnvConfigResult addFail(AppEnvConfigAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用环境
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppEnvConfigResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/env/appenvconfig/"+id;
        ResponseEntity<AppEnvConfigResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppEnvConfigResult>() {});
        return response.getBody();
    }
    private AppEnvConfigResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用环境
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppEnvConfigResult update(Long id, AppEnvConfigEditDto editDto) {
        String url = "http://"+host+"/speedcloud/env/appenvconfig/"+id;
        ResponseEntity<AppEnvConfigResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppEnvConfigResult>() {});
        return response.getBody();
    }

    public AppEnvConfigResult updateFail(Long id, AppEnvConfigEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用环境
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppEnvConfigResult get(Long id) {
        String url = "http://"+host+"/speedcloud/env/appenvconfig/"+id;
        return restTemplate.getForObject(url, AppEnvConfigResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppEnvConfigResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用环境列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppEnvConfigPageResult list(PageSearchRequest<AppEnvConfigCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/env/appenvconfig/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppEnvConfigPageResult.class);
    }
    public AppEnvConfigPageResult listFail(PageSearchRequest<AppEnvConfigCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AppEnvConfigPageResult result = new AppEnvConfigPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AppEnvConfigResult errorResult(){
        AppEnvConfigResult result = new AppEnvConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
