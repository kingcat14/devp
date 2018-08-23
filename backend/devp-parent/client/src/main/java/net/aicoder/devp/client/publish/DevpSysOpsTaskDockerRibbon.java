package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDockerPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskDockerResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
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
 * 部署容器客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskDockerRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDockerRibbon.class);

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
     * 新增部署容器
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskDockerResult add(DevpSysOpsTaskDockerAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDocker";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskDockerResult.class);
    }
    private DevpSysOpsTaskDockerResult addFail(DevpSysOpsTaskDockerAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署容器
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskDockerResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDocker/"+id;
        ResponseEntity<DevpSysOpsTaskDockerResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskDockerResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskDockerResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署容器
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskDockerResult update(Long id, DevpSysOpsTaskDockerEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDocker/"+id;
        ResponseEntity<DevpSysOpsTaskDockerResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskDockerResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskDockerResult updateFail(Long id, DevpSysOpsTaskDockerEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署容器
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskDockerResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDocker/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskDockerResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskDockerResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署容器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskDockerPageResult list(PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskDocker/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskDockerPageResult.class);
    }
    public DevpSysOpsTaskDockerPageResult listFail(PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskDockerPageResult result = new DevpSysOpsTaskDockerPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskDockerResult errorResult(){
        DevpSysOpsTaskDockerResult result = new DevpSysOpsTaskDockerResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
