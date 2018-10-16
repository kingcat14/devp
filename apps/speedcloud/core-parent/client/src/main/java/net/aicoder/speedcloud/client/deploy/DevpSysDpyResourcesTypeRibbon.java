package net.aicoder.speedcloud.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesTypePageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourcesTypeResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeEditDto;
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
public class DevpSysDpyResourcesTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTypeRibbon.class);

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
    public DevpSysDpyResourcesTypeResult add(DevpSysDpyResourcesTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcestype";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourcesTypeResult.class);
    }
    private DevpSysDpyResourcesTypeResult addFail(DevpSysDpyResourcesTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署资源类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourcesTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcestype/"+id;
        ResponseEntity<DevpSysDpyResourcesTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourcesTypeResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourcesTypeResult deleteFail(Long id, Throwable throwable) {

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
    public DevpSysDpyResourcesTypeResult update(Long id, DevpSysDpyResourcesTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcestype/"+id;
        ResponseEntity<DevpSysDpyResourcesTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourcesTypeResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourcesTypeResult updateFail(Long id, DevpSysDpyResourcesTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourcesTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcestype/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourcesTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourcesTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourcesTypePageResult list(PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcestype/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourcesTypePageResult.class);
    }
    public DevpSysDpyResourcesTypePageResult listFail(PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesTypePageResult result = new DevpSysDpyResourcesTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysDpyResourcesTypeResult errorResult(){
        DevpSysDpyResourcesTypeResult result = new DevpSysDpyResourcesTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
