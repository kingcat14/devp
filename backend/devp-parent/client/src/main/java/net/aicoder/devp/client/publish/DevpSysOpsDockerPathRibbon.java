package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerPathPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerPathResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
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
 * 存储路径定义客户端
 * @author icode
 */
@Service
public class DevpSysOpsDockerPathRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerPathRibbon.class);

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
     * 新增存储路径定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsDockerPathResult add(DevpSysOpsDockerPathAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerPath";
        return restTemplate.postForObject(url, addDto, DevpSysOpsDockerPathResult.class);
    }
    private DevpSysOpsDockerPathResult addFail(DevpSysOpsDockerPathAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除存储路径定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsDockerPathResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerPath/"+id;
        ResponseEntity<DevpSysOpsDockerPathResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsDockerPathResult>() {});
        return response.getBody();
    }
    private DevpSysOpsDockerPathResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新存储路径定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsDockerPathResult update(Long id, DevpSysOpsDockerPathEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerPath/"+id;
        ResponseEntity<DevpSysOpsDockerPathResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsDockerPathResult>() {});
        return response.getBody();
    }

    public DevpSysOpsDockerPathResult updateFail(Long id, DevpSysOpsDockerPathEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询存储路径定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsDockerPathResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerPath/"+id;
        return restTemplate.getForObject(url, DevpSysOpsDockerPathResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsDockerPathResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询存储路径定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsDockerPathPageResult list(PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsDockerPath/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsDockerPathPageResult.class);
    }
    public DevpSysOpsDockerPathPageResult listFail(PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsDockerPathPageResult result = new DevpSysOpsDockerPathPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsDockerPathResult errorResult(){
        DevpSysOpsDockerPathResult result = new DevpSysOpsDockerPathResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
