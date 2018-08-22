package net.aicoder.devp.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementEditDto;
import net.aicoder.devp.client.ops.result.DevpOpsRequirementPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsRequirementResult;

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
 * 需求定义客户端
 * @author icode
 */
@Service
public class DevpOpsRequirementRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsRequirementRibbon.class);

    private String host = "DEVP";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增需求定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsRequirementResult add(DevpOpsRequirementAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsRequirement";
        return restTemplate.postForObject(url, addDto, DevpOpsRequirementResult.class);
    }
    private DevpOpsRequirementResult addFail(DevpOpsRequirementAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsRequirementResult result = new DevpOpsRequirementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除需求定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsRequirementResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsRequirement/"+id;
        ResponseEntity<DevpOpsRequirementResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsRequirementResult>() {});
        return response.getBody();
    }
    private DevpOpsRequirementResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsRequirementResult result = new DevpOpsRequirementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新需求定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsRequirementResult update(Long id, DevpOpsRequirementEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsRequirement/"+id;
        ResponseEntity<DevpOpsRequirementResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsRequirementResult>() {});
        return response.getBody();
    }

    public DevpOpsRequirementResult updateFail(Long id, DevpOpsRequirementEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsRequirementResult result = new DevpOpsRequirementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询需求定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsRequirementResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsRequirement/"+id;
        return restTemplate.getForObject(url, DevpOpsRequirementResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsRequirementResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsRequirementResult result = new DevpOpsRequirementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询需求定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsRequirementPageResult list(PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsRequirement/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsRequirementPageResult.class);
    }
    public DevpOpsRequirementPageResult listFail(PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsRequirementPageResult result = new DevpOpsRequirementPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
