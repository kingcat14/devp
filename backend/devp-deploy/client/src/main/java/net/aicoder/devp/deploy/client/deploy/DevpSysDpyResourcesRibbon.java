package net.aicoder.devp.deploy.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResourcesPageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResourcesResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesEditDto;
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
 * 部署关联资源定义客户端
 * @author icode
 */
@Service
public class DevpSysDpyResourcesRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesRibbon.class);

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
     * 新增部署关联资源定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResourcesResult add(DevpSysDpyResourcesAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResources";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResourcesResult.class);
    }
    private DevpSysDpyResourcesResult addFail(DevpSysDpyResourcesAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesResult result = new DevpSysDpyResourcesResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除部署关联资源定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResourcesResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResources/"+id;
        ResponseEntity<DevpSysDpyResourcesResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResourcesResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResourcesResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesResult result = new DevpSysDpyResourcesResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新部署关联资源定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyResourcesResult update(Long id, DevpSysDpyResourcesEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResources/"+id;
        ResponseEntity<DevpSysDpyResourcesResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResourcesResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResourcesResult updateFail(Long id, DevpSysDpyResourcesEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesResult result = new DevpSysDpyResourcesResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询部署关联资源定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResourcesResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResources/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResourcesResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResourcesResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesResult result = new DevpSysDpyResourcesResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询部署关联资源定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResourcesPageResult list(PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyResources/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResourcesPageResult.class);
    }
    public DevpSysDpyResourcesPageResult listFail(PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResourcesPageResult result = new DevpSysDpyResourcesPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
