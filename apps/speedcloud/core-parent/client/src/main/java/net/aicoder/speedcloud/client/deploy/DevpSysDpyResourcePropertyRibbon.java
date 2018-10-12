package net.aicoder.speedcloud.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcePropertyPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcePropertyResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyEditDto;
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
public class DevpSysDpyResourcePropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcePropertyRibbon.class);

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
     * 新增资源属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResourcePropertyResult add(DevpSysDpyResourcePropertyAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceproperty";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourcePropertyResult.class);
    }
    private DevpSysDpyResourcePropertyResult addFail(DevpSysDpyResourcePropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资源属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourcePropertyResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceproperty/"+id;
        ResponseEntity<DevpSysDpyResourcePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourcePropertyResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourcePropertyResult deleteFail(Long id, Throwable throwable) {

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
    public DevpSysDpyResourcePropertyResult update(Long id, DevpSysDpyResourcePropertyEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceproperty/"+id;
        ResponseEntity<DevpSysDpyResourcePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourcePropertyResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourcePropertyResult updateFail(Long id, DevpSysDpyResourcePropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资源属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourcePropertyResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceproperty/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourcePropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourcePropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资源属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourcePropertyPageResult list(PageSearchRequest<DevpSysDpyResourcePropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourcePropertyPageResult.class);
    }
    public DevpSysDpyResourcePropertyPageResult listFail(PageSearchRequest<DevpSysDpyResourcePropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcePropertyPageResult result = new DevpSysDpyResourcePropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysDpyResourcePropertyResult errorResult(){
        DevpSysDpyResourcePropertyResult result = new DevpSysDpyResourcePropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
