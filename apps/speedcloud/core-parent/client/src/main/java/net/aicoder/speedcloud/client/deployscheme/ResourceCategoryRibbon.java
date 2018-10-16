package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceCategoryPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourceCategoryResult;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
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
 * 部署资源类别客户端
 * @author icode
 */
@Service
public class ResourceCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryRibbon.class);

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
     * 新增部署资源类别
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ResourceCategoryResult add(ResourceCategoryAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcecategory";
        return restTemplate.postForObject(url, addDto, ResourceCategoryResult.class);
    }
    private ResourceCategoryResult addFail(ResourceCategoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourceCategoryResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcecategory/"+id;
        ResponseEntity<ResourceCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourceCategoryResult>() {});
        return response.getBody();
    }
    private ResourceCategoryResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署资源类别
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ResourceCategoryResult update(Long id, ResourceCategoryEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcecategory/"+id;
        ResponseEntity<ResourceCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourceCategoryResult>() {});
        return response.getBody();
    }

    public ResourceCategoryResult updateFail(Long id, ResourceCategoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourceCategoryResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcecategory/"+id;
        return restTemplate.getForObject(url, ResourceCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourceCategoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourceCategoryPageResult list(PageSearchRequest<ResourceCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcecategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourceCategoryPageResult.class);
    }
    public ResourceCategoryPageResult listFail(PageSearchRequest<ResourceCategoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourceCategoryPageResult result = new ResourceCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourceCategoryResult errorResult(){
        ResourceCategoryResult result = new ResourceCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
