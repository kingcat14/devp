package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcesCategoryEditDto;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcesCategoryPageResult;
import net.aicoder.speedcloud.client.deployscheme.result.ResourcesCategoryResult;
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
public class ResourcesCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesCategoryRibbon.class);

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
    public ResourcesCategoryResult add(ResourcesCategoryAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcescategory";
        return restTemplate.postForObject(url, addDto, ResourcesCategoryResult.class);
    }
    private ResourcesCategoryResult addFail(ResourcesCategoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ResourcesCategoryResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcescategory/"+id;
        ResponseEntity<ResourcesCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ResourcesCategoryResult>() {});
        return response.getBody();
    }
    private ResourcesCategoryResult deleteFail(Long id, Throwable throwable) {

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
    public ResourcesCategoryResult update(Long id, ResourcesCategoryEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcescategory/"+id;
        ResponseEntity<ResourcesCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ResourcesCategoryResult>() {});
        return response.getBody();
    }

    public ResourcesCategoryResult updateFail(Long id, ResourcesCategoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ResourcesCategoryResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcescategory/"+id;
        return restTemplate.getForObject(url, ResourcesCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ResourcesCategoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ResourcesCategoryPageResult list(PageSearchRequest<ResourcesCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/resourcescategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, ResourcesCategoryPageResult.class);
    }
    public ResourcesCategoryPageResult listFail(PageSearchRequest<ResourcesCategoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ResourcesCategoryPageResult result = new ResourcesCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ResourcesCategoryResult errorResult(){
        ResourcesCategoryResult result = new ResourcesCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
