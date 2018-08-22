package net.aicoder.devp.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.client.deploy.result.DevpSysDpyHostPageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpyHostResult;

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
 * 部署主机节点客户端
 * @author icode
 */
@Service
public class DevpSysDpyHostRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostRibbon.class);

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
     * 新增部署主机节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyHostResult add(DevpSysDpyHostAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyHost";
        return restTemplate.postForObject(url, addDto, DevpSysDpyHostResult.class);
    }
    private DevpSysDpyHostResult addFail(DevpSysDpyHostAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyHostResult result = new DevpSysDpyHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除部署主机节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyHostResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyHost/"+id;
        ResponseEntity<DevpSysDpyHostResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyHostResult>() {});
        return response.getBody();
    }
    private DevpSysDpyHostResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyHostResult result = new DevpSysDpyHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新部署主机节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyHostResult update(Long id, DevpSysDpyHostEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyHost/"+id;
        ResponseEntity<DevpSysDpyHostResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyHostResult>() {});
        return response.getBody();
    }

    public DevpSysDpyHostResult updateFail(Long id, DevpSysDpyHostEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyHostResult result = new DevpSysDpyHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyHostResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyHost/"+id;
        return restTemplate.getForObject(url, DevpSysDpyHostResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyHostResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyHostResult result = new DevpSysDpyHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyHostPageResult list(PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyHost/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyHostPageResult.class);
    }
    public DevpSysDpyHostPageResult listFail(PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyHostPageResult result = new DevpSysDpyHostPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
