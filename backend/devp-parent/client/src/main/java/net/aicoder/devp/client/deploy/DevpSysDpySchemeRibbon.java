package net.aicoder.devp.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeEditDto;
import net.aicoder.devp.client.deploy.result.DevpSysDpySchemePageResult;
import net.aicoder.devp.client.deploy.result.DevpSysDpySchemeResult;

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
 * 产品部署方案客户端
 * @author icode
 */
@Service
public class DevpSysDpySchemeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpySchemeRibbon.class);

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
     * 新增产品部署方案
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpySchemeResult add(DevpSysDpySchemeAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyScheme";
        return restTemplate.postForObject(url, addDto, DevpSysDpySchemeResult.class);
    }
    private DevpSysDpySchemeResult addFail(DevpSysDpySchemeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpySchemeResult result = new DevpSysDpySchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品部署方案
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpySchemeResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyScheme/"+id;
        ResponseEntity<DevpSysDpySchemeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpySchemeResult>() {});
        return response.getBody();
    }
    private DevpSysDpySchemeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpySchemeResult result = new DevpSysDpySchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品部署方案
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpySchemeResult update(Long id, DevpSysDpySchemeEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyScheme/"+id;
        ResponseEntity<DevpSysDpySchemeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpySchemeResult>() {});
        return response.getBody();
    }

    public DevpSysDpySchemeResult updateFail(Long id, DevpSysDpySchemeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpySchemeResult result = new DevpSysDpySchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品部署方案
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpySchemeResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyScheme/"+id;
        return restTemplate.getForObject(url, DevpSysDpySchemeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpySchemeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpySchemeResult result = new DevpSysDpySchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpySchemePageResult list(PageSearchRequest<DevpSysDpySchemeCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyScheme/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpySchemePageResult.class);
    }
    public DevpSysDpySchemePageResult listFail(PageSearchRequest<DevpSysDpySchemeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpySchemePageResult result = new DevpSysDpySchemePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
