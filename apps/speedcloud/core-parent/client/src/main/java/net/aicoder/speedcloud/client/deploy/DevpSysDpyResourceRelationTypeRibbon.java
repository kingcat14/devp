package net.aicoder.speedcloud.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRelationTypePageResult;
import net.aicoder.speedcloud.client.deploy.result.DevpSysDpyResourceRelationTypeResult;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeEditDto;
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
public class DevpSysDpyResourceRelationTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRelationTypeRibbon.class);

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
     * 新增资源关系类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResourceRelationTypeResult add(DevpSysDpyResourceRelationTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcerelationtype";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourceRelationTypeResult.class);
    }
    private DevpSysDpyResourceRelationTypeResult addFail(DevpSysDpyResourceRelationTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资源关系类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourceRelationTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcerelationtype/"+id;
        ResponseEntity<DevpSysDpyResourceRelationTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourceRelationTypeResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourceRelationTypeResult deleteFail(Long id, Throwable throwable) {

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
    public DevpSysDpyResourceRelationTypeResult update(Long id, DevpSysDpyResourceRelationTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcerelationtype/"+id;
        ResponseEntity<DevpSysDpyResourceRelationTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourceRelationTypeResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourceRelationTypeResult updateFail(Long id, DevpSysDpyResourceRelationTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资源关系类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourceRelationTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcerelationtype/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourceRelationTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourceRelationTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资源关系类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourceRelationTypePageResult list(PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deploy/devpsysdpyresourcerelationtype/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourceRelationTypePageResult.class);
    }
    public DevpSysDpyResourceRelationTypePageResult listFail(PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourceRelationTypePageResult result = new DevpSysDpyResourceRelationTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysDpyResourceRelationTypeResult errorResult(){
        DevpSysDpyResourceRelationTypeResult result = new DevpSysDpyResourceRelationTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
