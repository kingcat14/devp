package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.result.AppDevelopConfigPageResult;
import net.aicoder.speedcloud.client.app.result.AppDevelopConfigResult;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
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
 * 应用开发配置客户端
 * @author icode
 */
@Service
public class AppDevelopConfigRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigRibbon.class);

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
     * 新增应用开发配置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppDevelopConfigResult add(AppDevelopConfigAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/appdevelopconfig";
        return restTemplate.postForObject(url, addDto, AppDevelopConfigResult.class);
    }
    private AppDevelopConfigResult addFail(AppDevelopConfigAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用开发配置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppDevelopConfigResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/appdevelopconfig/"+id;
        ResponseEntity<AppDevelopConfigResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppDevelopConfigResult>() {});
        return response.getBody();
    }
    private AppDevelopConfigResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用开发配置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppDevelopConfigResult update(Long id, AppDevelopConfigEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/appdevelopconfig/"+id;
        ResponseEntity<AppDevelopConfigResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppDevelopConfigResult>() {});
        return response.getBody();
    }

    public AppDevelopConfigResult updateFail(Long id, AppDevelopConfigEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用开发配置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppDevelopConfigResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/appdevelopconfig/"+id;
        return restTemplate.getForObject(url, AppDevelopConfigResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppDevelopConfigResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用开发配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppDevelopConfigPageResult list(PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/appdevelopconfig/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppDevelopConfigPageResult.class);
    }
    public AppDevelopConfigPageResult listFail(PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AppDevelopConfigPageResult result = new AppDevelopConfigPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AppDevelopConfigResult errorResult(){
        AppDevelopConfigResult result = new AppDevelopConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
