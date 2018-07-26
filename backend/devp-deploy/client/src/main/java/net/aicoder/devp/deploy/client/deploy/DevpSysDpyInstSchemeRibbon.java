package net.aicoder.devp.deploy.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyInstSchemePageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyInstSchemeResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
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
 * 资源实例所属的部署方案客户端
 * @author icode
 */
@Service
public class DevpSysDpyInstSchemeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyInstSchemeRibbon.class);

    private String host = "DEPLOY";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增资源实例所属的部署方案
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyInstSchemeResult add(DevpSysDpyInstSchemeAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyInstScheme";
        return restTemplate.postForObject(url, addDto, DevpSysDpyInstSchemeResult.class);
    }
    private DevpSysDpyInstSchemeResult addFail(DevpSysDpyInstSchemeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyInstSchemeResult result = new DevpSysDpyInstSchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除资源实例所属的部署方案
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyInstSchemeResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyInstScheme/"+id;
        ResponseEntity<DevpSysDpyInstSchemeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyInstSchemeResult>() {});
        return response.getBody();
    }
    private DevpSysDpyInstSchemeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyInstSchemeResult result = new DevpSysDpyInstSchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新资源实例所属的部署方案
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyInstSchemeResult update(Long id, DevpSysDpyInstSchemeEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyInstScheme/"+id;
        ResponseEntity<DevpSysDpyInstSchemeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyInstSchemeResult>() {});
        return response.getBody();
    }

    public DevpSysDpyInstSchemeResult updateFail(Long id, DevpSysDpyInstSchemeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyInstSchemeResult result = new DevpSysDpyInstSchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询资源实例所属的部署方案
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyInstSchemeResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyInstScheme/"+id;
        return restTemplate.getForObject(url, DevpSysDpyInstSchemeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyInstSchemeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyInstSchemeResult result = new DevpSysDpyInstSchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询资源实例所属的部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyInstSchemePageResult list(PageSearchRequest<DevpSysDpyInstSchemeCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyInstScheme/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyInstSchemePageResult.class);
    }
    public DevpSysDpyInstSchemePageResult listFail(PageSearchRequest<DevpSysDpyInstSchemeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyInstSchemePageResult result = new DevpSysDpyInstSchemePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
