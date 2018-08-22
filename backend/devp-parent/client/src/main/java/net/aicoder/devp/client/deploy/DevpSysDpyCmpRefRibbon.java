package net.aicoder.devp.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.client.deploy.result.DevpSysDpyCmpRefPageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpyCmpRefResult;

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
 * 系统元素间关系客户端
 * @author icode
 */
@Service
public class DevpSysDpyCmpRefRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpRefRibbon.class);

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
     * 新增系统元素间关系
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyCmpRefResult add(DevpSysDpyCmpRefAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyCmpRef";
        return restTemplate.postForObject(url, addDto, DevpSysDpyCmpRefResult.class);
    }
    private DevpSysDpyCmpRefResult addFail(DevpSysDpyCmpRefAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyCmpRefResult result = new DevpSysDpyCmpRefResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素间关系
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyCmpRefResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyCmpRef/"+id;
        ResponseEntity<DevpSysDpyCmpRefResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyCmpRefResult>() {});
        return response.getBody();
    }
    private DevpSysDpyCmpRefResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyCmpRefResult result = new DevpSysDpyCmpRefResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素间关系
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyCmpRefResult update(Long id, DevpSysDpyCmpRefEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyCmpRef/"+id;
        ResponseEntity<DevpSysDpyCmpRefResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyCmpRefResult>() {});
        return response.getBody();
    }

    public DevpSysDpyCmpRefResult updateFail(Long id, DevpSysDpyCmpRefEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyCmpRefResult result = new DevpSysDpyCmpRefResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyCmpRefResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyCmpRef/"+id;
        return restTemplate.getForObject(url, DevpSysDpyCmpRefResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyCmpRefResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyCmpRefResult result = new DevpSysDpyCmpRefResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyCmpRefPageResult list(PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyCmpRef/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyCmpRefPageResult.class);
    }
    public DevpSysDpyCmpRefPageResult listFail(PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyCmpRefPageResult result = new DevpSysDpyCmpRefPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
