package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceInstanceRelationPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceInstanceRelationResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationEditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * 方案资源关联实例客户端
 * @author icode
 */
@Service
public class ResourceInstanceRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInstanceRelationRibbon.class);

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
     * 新增方案资源关联实例
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceInstanceRelationResult add(ResourceInstanceRelationAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation";
        return restTemplate.postForObject(url, addDto, ResourceInstanceRelationResult.class);
    }
    private ResourceInstanceRelationResult addFail(ResourceInstanceRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除方案资源关联实例
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceInstanceRelationResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation/"+id;
        ResponseEntity<ResourceInstanceRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceInstanceRelationResult>() {});
        return response.getBody();
    }
    private ResourceInstanceRelationResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新方案资源关联实例
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceInstanceRelationResult update(Long id, ResourceInstanceRelationEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation/"+id;
        ResponseEntity<ResourceInstanceRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceInstanceRelationResult>() {});
        return response.getBody();
    }

    public ResourceInstanceRelationResult updateFail(Long id, ResourceInstanceRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }
    /**
     * 更新方案资源关联的所有实例
     * @param id
     * @param editDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "updateAllFail")
    public ResourceInstanceRelationResult updateAll(Long id, List<ResourceInstanceRelationAddDto> editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation/updateall/"+id;
        ResponseEntity<ResourceInstanceRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceInstanceRelationResult>() {});
        return response.getBody();
    }

    public ResourceInstanceRelationResult updateAllFail(Long id, ResourceInstanceRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询方案资源关联实例
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceInstanceRelationResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation/"+id;
        return restTemplate.getForObject(url, ResourceInstanceRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceInstanceRelationResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询方案资源关联实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourceInstanceRelationPageResult list(PageSearchRequest<ResourceInstanceRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourceinstancerelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourceInstanceRelationPageResult.class);
    }
    public ResourceInstanceRelationPageResult listFail(PageSearchRequest<ResourceInstanceRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourceInstanceRelationPageResult result = new ResourceInstanceRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceInstanceRelationResult errorResult(){
        ResourceInstanceRelationResult result = new ResourceInstanceRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
