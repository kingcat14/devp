package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesTypeEditDto;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcesTypePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcesTypeResult;
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
public class ResourcesTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesTypeRibbon.class);

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
    public ResourcesTypeResult add(ResourcesTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcestype";
        return restTemplate.postForObject(url, addDto, ResourcesTypeResult.class);
    }
    private ResourcesTypeResult addFail(ResourcesTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourcesTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcestype/"+id;
        ResponseEntity<ResourcesTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourcesTypeResult>() {});
        return response.getBody();
    }
    private ResourcesTypeResult deleteFail(Long id, Throwable throwable) {

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
    public ResourcesTypeResult update(Long id, ResourcesTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcestype/"+id;
        ResponseEntity<ResourcesTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourcesTypeResult>() {});
        return response.getBody();
    }

    public ResourcesTypeResult updateFail(Long id, ResourcesTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourcesTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcestype/"+id;
        return restTemplate.getForObject(url, ResourcesTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourcesTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourcesTypePageResult list(PageSearchRequest<ResourcesTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcestype/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourcesTypePageResult.class);
    }
    public ResourcesTypePageResult listFail(PageSearchRequest<ResourcesTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourcesTypePageResult result = new ResourcesTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourcesTypeResult errorResult(){
        ResourcesTypeResult result = new ResourcesTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
