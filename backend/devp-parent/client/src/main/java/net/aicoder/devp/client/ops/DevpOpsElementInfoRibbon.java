package net.aicoder.devp.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.client.ops.result.DevpOpsElementInfoPageResult;
import net.aicoder.devp.client.ops.result.DevpOpsElementInfoResult;

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
 * 系统元素扩充信息客户端
 * @author icode
 */
@Service
public class DevpOpsElementInfoRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoRibbon.class);

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
     * 新增系统元素扩充信息
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsElementInfoResult add(DevpOpsElementInfoAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsElementInfo";
        return restTemplate.postForObject(url, addDto, DevpOpsElementInfoResult.class);
    }
    private DevpOpsElementInfoResult addFail(DevpOpsElementInfoAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsElementInfoResult result = new DevpOpsElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素扩充信息
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsElementInfoResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsElementInfo/"+id;
        ResponseEntity<DevpOpsElementInfoResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsElementInfoResult>() {});
        return response.getBody();
    }
    private DevpOpsElementInfoResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsElementInfoResult result = new DevpOpsElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素扩充信息
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsElementInfoResult update(Long id, DevpOpsElementInfoEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsElementInfo/"+id;
        ResponseEntity<DevpOpsElementInfoResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsElementInfoResult>() {});
        return response.getBody();
    }

    public DevpOpsElementInfoResult updateFail(Long id, DevpOpsElementInfoEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsElementInfoResult result = new DevpOpsElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsElementInfoResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsElementInfo/"+id;
        return restTemplate.getForObject(url, DevpOpsElementInfoResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsElementInfoResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsElementInfoResult result = new DevpOpsElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsElementInfoPageResult list(PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsElementInfo/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsElementInfoPageResult.class);
    }
    public DevpOpsElementInfoPageResult listFail(PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsElementInfoPageResult result = new DevpOpsElementInfoPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
