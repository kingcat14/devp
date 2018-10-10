package net.aicoder.speedcloud.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesCategoryPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesCategoryResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryEditDto;
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
public class DevpSysDpyResourcesCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesCategoryRibbon.class);

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
     * 新增部署资源类别
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResourcesCategoryResult add(DevpSysDpyResourcesCategoryAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcescategory";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourcesCategoryResult.class);
    }
    private DevpSysDpyResourcesCategoryResult addFail(DevpSysDpyResourcesCategoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourcesCategoryResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcescategory/"+id;
        ResponseEntity<DevpSysDpyResourcesCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourcesCategoryResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourcesCategoryResult deleteFail(Long id, Throwable throwable) {

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
    public DevpSysDpyResourcesCategoryResult update(Long id, DevpSysDpyResourcesCategoryEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcescategory/"+id;
        ResponseEntity<DevpSysDpyResourcesCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourcesCategoryResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourcesCategoryResult updateFail(Long id, DevpSysDpyResourcesCategoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourcesCategoryResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcescategory/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourcesCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourcesCategoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourcesCategoryPageResult list(PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcescategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourcesCategoryPageResult.class);
    }
    public DevpSysDpyResourcesCategoryPageResult listFail(PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesCategoryPageResult result = new DevpSysDpyResourcesCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysDpyResourcesCategoryResult errorResult(){
        DevpSysDpyResourcesCategoryResult result = new DevpSysDpyResourcesCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
