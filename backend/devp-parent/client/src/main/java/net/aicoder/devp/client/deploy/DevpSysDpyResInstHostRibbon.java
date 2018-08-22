package net.aicoder.devp.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.client.deploy.result.DevpSysDpyResInstHostPageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpyResInstHostResult;

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
 * 资源实例部署主机节点客户端
 * @author icode
 */
@Service
public class DevpSysDpyResInstHostRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostRibbon.class);

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
     * 新增资源实例部署主机节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResInstHostResult add(DevpSysDpyResInstHostAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResInstHost";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResInstHostResult.class);
    }
    private DevpSysDpyResInstHostResult addFail(DevpSysDpyResInstHostAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstHostResult result = new DevpSysDpyResInstHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除资源实例部署主机节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResInstHostResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResInstHost/"+id;
        ResponseEntity<DevpSysDpyResInstHostResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResInstHostResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResInstHostResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstHostResult result = new DevpSysDpyResInstHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新资源实例部署主机节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyResInstHostResult update(Long id, DevpSysDpyResInstHostEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResInstHost/"+id;
        ResponseEntity<DevpSysDpyResInstHostResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResInstHostResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResInstHostResult updateFail(Long id, DevpSysDpyResInstHostEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstHostResult result = new DevpSysDpyResInstHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询资源实例部署主机节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResInstHostResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResInstHost/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResInstHostResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResInstHostResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstHostResult result = new DevpSysDpyResInstHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询资源实例部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResInstHostPageResult list(PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyResInstHost/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResInstHostPageResult.class);
    }
    public DevpSysDpyResInstHostPageResult listFail(PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstHostPageResult result = new DevpSysDpyResInstHostPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
