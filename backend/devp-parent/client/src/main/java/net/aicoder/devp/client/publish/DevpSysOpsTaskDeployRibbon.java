package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDeployPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDeployResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployEditDto;
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
 * 组件部署设置客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskDeployRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDeployRibbon.class);

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
     * 新增组件部署设置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskDeployResult add(DevpSysOpsTaskDeployAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDeploy";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskDeployResult.class);
    }
    private DevpSysOpsTaskDeployResult addFail(DevpSysOpsTaskDeployAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除组件部署设置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskDeployResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDeploy/"+id;
        ResponseEntity<DevpSysOpsTaskDeployResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskDeployResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskDeployResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新组件部署设置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskDeployResult update(Long id, DevpSysOpsTaskDeployEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDeploy/"+id;
        ResponseEntity<DevpSysOpsTaskDeployResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskDeployResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskDeployResult updateFail(Long id, DevpSysOpsTaskDeployEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询组件部署设置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskDeployResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDeploy/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskDeployResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskDeployResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询组件部署设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskDeployPageResult list(PageSearchRequest<DevpSysOpsTaskDeployCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDeploy/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskDeployPageResult.class);
    }
    public DevpSysOpsTaskDeployPageResult listFail(PageSearchRequest<DevpSysOpsTaskDeployCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskDeployPageResult result = new DevpSysOpsTaskDeployPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskDeployResult errorResult(){
        DevpSysOpsTaskDeployResult result = new DevpSysOpsTaskDeployResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
