package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeEditDto;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationTypePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceRelationTypeResult;
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
 * 资源关系类型客户端
 * @author icode
 */
@Service
public class ResourceRelationTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationTypeRibbon.class);

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
     * 新增资源关系类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceRelationTypeResult add(ResourceRelationTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelationtype";
        return restTemplate.postForObject(url, addDto, ResourceRelationTypeResult.class);
    }
    private ResourceRelationTypeResult addFail(ResourceRelationTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资源关系类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceRelationTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelationtype/"+id;
        ResponseEntity<ResourceRelationTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceRelationTypeResult>() {});
        return response.getBody();
    }
    private ResourceRelationTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资源关系类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceRelationTypeResult update(Long id, ResourceRelationTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelationtype/"+id;
        ResponseEntity<ResourceRelationTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceRelationTypeResult>() {});
        return response.getBody();
    }

    public ResourceRelationTypeResult updateFail(Long id, ResourceRelationTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资源关系类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceRelationTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelationtype/"+id;
        return restTemplate.getForObject(url, ResourceRelationTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceRelationTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资源关系类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourceRelationTypePageResult list(PageSearchRequest<ResourceRelationTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcerelationtype/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourceRelationTypePageResult.class);
    }
    public ResourceRelationTypePageResult listFail(PageSearchRequest<ResourceRelationTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourceRelationTypePageResult result = new ResourceRelationTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceRelationTypeResult errorResult(){
        ResourceRelationTypeResult result = new ResourceRelationTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
