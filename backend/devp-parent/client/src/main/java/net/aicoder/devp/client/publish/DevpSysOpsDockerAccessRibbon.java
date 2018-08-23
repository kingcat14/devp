package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerAccessPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerAccessResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessEditDto;
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
 * 部署容器访问参数客户端
 * @author icode
 */
@Service
public class DevpSysOpsDockerAccessRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerAccessRibbon.class);

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
     * 新增部署容器访问参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsDockerAccessResult add(DevpSysOpsDockerAccessAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerAccess";
        return restTemplate.postForObject(url, addDto, DevpSysOpsDockerAccessResult.class);
    }
    private DevpSysOpsDockerAccessResult addFail(DevpSysOpsDockerAccessAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署容器访问参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsDockerAccessResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerAccess/"+id;
        ResponseEntity<DevpSysOpsDockerAccessResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsDockerAccessResult>() {});
        return response.getBody();
    }
    private DevpSysOpsDockerAccessResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署容器访问参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsDockerAccessResult update(Long id, DevpSysOpsDockerAccessEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerAccess/"+id;
        ResponseEntity<DevpSysOpsDockerAccessResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsDockerAccessResult>() {});
        return response.getBody();
    }

    public DevpSysOpsDockerAccessResult updateFail(Long id, DevpSysOpsDockerAccessEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署容器访问参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsDockerAccessResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerAccess/"+id;
        return restTemplate.getForObject(url, DevpSysOpsDockerAccessResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsDockerAccessResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署容器访问参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsDockerAccessPageResult list(PageSearchRequest<DevpSysOpsDockerAccessCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsDockerAccess/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsDockerAccessPageResult.class);
    }
    public DevpSysOpsDockerAccessPageResult listFail(PageSearchRequest<DevpSysOpsDockerAccessCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsDockerAccessPageResult result = new DevpSysOpsDockerAccessPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsDockerAccessResult errorResult(){
        DevpSysOpsDockerAccessResult result = new DevpSysOpsDockerAccessResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
