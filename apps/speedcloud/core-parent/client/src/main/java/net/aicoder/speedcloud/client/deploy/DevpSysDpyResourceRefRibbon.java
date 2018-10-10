package net.aicoder.speedcloud.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRefPageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRefResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefEditDto;
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
public class DevpSysDpyResourceRefRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRefRibbon.class);

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
     * 新增方案资源间关系
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResourceRefResult add(DevpSysDpyResourceRefAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceref";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourceRefResult.class);
    }
    private DevpSysDpyResourceRefResult addFail(DevpSysDpyResourceRefAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除方案资源间关系
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourceRefResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceref/"+id;
        ResponseEntity<DevpSysDpyResourceRefResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourceRefResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourceRefResult deleteFail(Long id, Throwable throwable) {

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
    public DevpSysDpyResourceRefResult update(Long id, DevpSysDpyResourceRefEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceref/"+id;
        ResponseEntity<DevpSysDpyResourceRefResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourceRefResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourceRefResult updateFail(Long id, DevpSysDpyResourceRefEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询方案资源间关系
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourceRefResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceref/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourceRefResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourceRefResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询方案资源间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourceRefPageResult list(PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourceref/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourceRefPageResult.class);
    }
    public DevpSysDpyResourceRefPageResult listFail(PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourceRefPageResult result = new DevpSysDpyResourceRefPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysDpyResourceRefResult errorResult(){
        DevpSysDpyResourceRefResult result = new DevpSysDpyResourceRefResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
