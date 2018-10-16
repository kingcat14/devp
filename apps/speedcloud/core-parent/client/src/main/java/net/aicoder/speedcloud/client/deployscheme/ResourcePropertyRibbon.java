package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePropertyPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcePropertyResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyEditDto;
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
 * 资源属性客户端
 * @author icode
 */
@Service
public class ResourcePropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePropertyRibbon.class);

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
     * 新增资源属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourcePropertyResult add(ResourcePropertyAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceproperty";
        return restTemplate.postForObject(url, addDto, ResourcePropertyResult.class);
    }
    private ResourcePropertyResult addFail(ResourcePropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资源属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourcePropertyResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceproperty/"+id;
        ResponseEntity<ResourcePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourcePropertyResult>() {});
        return response.getBody();
    }
    private ResourcePropertyResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资源属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourcePropertyResult update(Long id, ResourcePropertyEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceproperty/"+id;
        ResponseEntity<ResourcePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourcePropertyResult>() {});
        return response.getBody();
    }

    public ResourcePropertyResult updateFail(Long id, ResourcePropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资源属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourcePropertyResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceproperty/"+id;
        return restTemplate.getForObject(url, ResourcePropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourcePropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资源属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourcePropertyPageResult list(PageSearchRequest<ResourcePropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourcePropertyPageResult.class);
    }
    public ResourcePropertyPageResult listFail(PageSearchRequest<ResourcePropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourcePropertyPageResult result = new ResourcePropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourcePropertyResult errorResult(){
        ResourcePropertyResult result = new ResourcePropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
