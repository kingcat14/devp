package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationEditDto;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationResult;
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
 * 方案资源间关系客户端
 * @author icode
 */
@Service
public class ResourceRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationRibbon.class);

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
     * 新增方案资源间关系
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceRelationResult add(ResourceRelationAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelation";
        return restTemplate.postForObject(url, addDto, ResourceRelationResult.class);
    }
    private ResourceRelationResult addFail(ResourceRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除方案资源间关系
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceRelationResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelation/"+id;
        ResponseEntity<ResourceRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceRelationResult>() {});
        return response.getBody();
    }
    private ResourceRelationResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新方案资源间关系
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceRelationResult update(Long id, ResourceRelationEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelation/"+id;
        ResponseEntity<ResourceRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceRelationResult>() {});
        return response.getBody();
    }

    public ResourceRelationResult updateFail(Long id, ResourceRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询方案资源间关系
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceRelationResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelation/"+id;
        return restTemplate.getForObject(url, ResourceRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceRelationResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询方案资源间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourceRelationPageResult list(PageSearchRequest<ResourceRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourceRelationPageResult.class);
    }
    public ResourceRelationPageResult listFail(PageSearchRequest<ResourceRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourceRelationPageResult result = new ResourceRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceRelationResult errorResult(){
        ResourceRelationResult result = new ResourceRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
