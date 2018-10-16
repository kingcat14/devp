package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceEditDto;
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
 * 方案资源客户端
 * @author icode
 */
@Service
public class ResourceRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRibbon.class);

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
     * 新增方案资源
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceResult add(ResourceAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resource";
        return restTemplate.postForObject(url, addDto, ResourceResult.class);
    }
    private ResourceResult addFail(ResourceAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除方案资源
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resource/"+id;
        ResponseEntity<ResourceResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceResult>() {});
        return response.getBody();
    }
    private ResourceResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新方案资源
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceResult update(Long id, ResourceEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resource/"+id;
        ResponseEntity<ResourceResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceResult>() {});
        return response.getBody();
    }

    public ResourceResult updateFail(Long id, ResourceEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询方案资源
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resource/"+id;
        return restTemplate.getForObject(url, ResourceResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询方案资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourcePageResult list(PageSearchRequest<ResourceCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resource/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourcePageResult.class);
    }
    public ResourcePageResult listFail(PageSearchRequest<ResourceCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourcePageResult result = new ResourcePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceResult errorResult(){
        ResourceResult result = new ResourceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
