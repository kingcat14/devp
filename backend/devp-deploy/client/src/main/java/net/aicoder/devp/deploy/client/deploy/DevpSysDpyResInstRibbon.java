package net.aicoder.devp.deploy.client.deploy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResInstPageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyResInstResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstEditDto;
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
 * 部署关联资源实例定义客户端
 * @author icode
 */
@Service
public class DevpSysDpyResInstRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstRibbon.class);

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
     * 新增部署关联资源实例定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDpyResInstResult add(DevpSysDpyResInstAddDto addDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResInst";
        return restTemplate.postForObject(url, addDto, DevpSysDpyResInstResult.class);
    }
    private DevpSysDpyResInstResult addFail(DevpSysDpyResInstAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstResult result = new DevpSysDpyResInstResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除部署关联资源实例定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDpyResInstResult delete(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResInst/"+id;
        ResponseEntity<DevpSysDpyResInstResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDpyResInstResult>() {});
        return response.getBody();
    }
    private DevpSysDpyResInstResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstResult result = new DevpSysDpyResInstResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新部署关联资源实例定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDpyResInstResult update(Long id, DevpSysDpyResInstEditDto editDto) {
        String url = "http://"+host+"/deploy/devpSysDpyResInst/"+id;
        ResponseEntity<DevpSysDpyResInstResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDpyResInstResult>() {});
        return response.getBody();
    }

    public DevpSysDpyResInstResult updateFail(Long id, DevpSysDpyResInstEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstResult result = new DevpSysDpyResInstResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询部署关联资源实例定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDpyResInstResult get(Long id) {
        String url = "http://"+host+"/deploy/devpSysDpyResInst/"+id;
        return restTemplate.getForObject(url, DevpSysDpyResInstResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDpyResInstResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstResult result = new DevpSysDpyResInstResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询部署关联资源实例定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDpyResInstPageResult list(PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest) {
        String url = "http://"+host+"/deploy/devpSysDpyResInst/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDpyResInstPageResult.class);
    }
    public DevpSysDpyResInstPageResult listFail(PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysDpyResInstPageResult result = new DevpSysDpyResInstPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
