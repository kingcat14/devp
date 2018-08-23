package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerParamPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsDockerParamResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
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
 * 部署容器参数定义客户端
 * @author icode
 */
@Service
public class DevpSysOpsDockerParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerParamRibbon.class);

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
     * 新增部署容器参数定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsDockerParamResult add(DevpSysOpsDockerParamAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerParam";
        return restTemplate.postForObject(url, addDto, DevpSysOpsDockerParamResult.class);
    }
    private DevpSysOpsDockerParamResult addFail(DevpSysOpsDockerParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署容器参数定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsDockerParamResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerParam/"+id;
        ResponseEntity<DevpSysOpsDockerParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsDockerParamResult>() {});
        return response.getBody();
    }
    private DevpSysOpsDockerParamResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署容器参数定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsDockerParamResult update(Long id, DevpSysOpsDockerParamEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsDockerParam/"+id;
        ResponseEntity<DevpSysOpsDockerParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsDockerParamResult>() {});
        return response.getBody();
    }

    public DevpSysOpsDockerParamResult updateFail(Long id, DevpSysOpsDockerParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署容器参数定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsDockerParamResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsDockerParam/"+id;
        return restTemplate.getForObject(url, DevpSysOpsDockerParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsDockerParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署容器参数定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsDockerParamPageResult list(PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsDockerParam/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsDockerParamPageResult.class);
    }
    public DevpSysOpsDockerParamPageResult listFail(PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsDockerParamPageResult result = new DevpSysOpsDockerParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsDockerParamResult errorResult(){
        DevpSysOpsDockerParamResult result = new DevpSysOpsDockerParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
