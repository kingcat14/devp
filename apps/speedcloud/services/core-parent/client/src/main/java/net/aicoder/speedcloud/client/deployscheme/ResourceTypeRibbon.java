package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeEditDto;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceTypePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceTypeResult;
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
 * 部署资源类型客户端
 * @author icode
 */
@Service
public class ResourceTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeRibbon.class);

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
     * 新增部署资源类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceTypeResult add(ResourceTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcetype";
        return restTemplate.postForObject(url, addDto, ResourceTypeResult.class);
    }
    private ResourceTypeResult addFail(ResourceTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcetype/"+id;
        ResponseEntity<ResourceTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceTypeResult>() {});
        return response.getBody();
    }
    private ResourceTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署资源类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceTypeResult update(Long id, ResourceTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcetype/"+id;
        ResponseEntity<ResourceTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceTypeResult>() {});
        return response.getBody();
    }

    public ResourceTypeResult updateFail(Long id, ResourceTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcetype/"+id;
        return restTemplate.getForObject(url, ResourceTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourceTypePageResult list(PageSearchRequest<ResourceTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcetype/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourceTypePageResult.class);
    }
    public ResourceTypePageResult listFail(PageSearchRequest<ResourceTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourceTypePageResult result = new ResourceTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceTypeResult errorResult(){
        ResourceTypeResult result = new ResourceTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
