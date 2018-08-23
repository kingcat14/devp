package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskConfigPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskConfigResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigEditDto;
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
 * 配置文件设置客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskConfigRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskConfigRibbon.class);

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
     * 新增配置文件设置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskConfigResult add(DevpSysOpsTaskConfigAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskConfig";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskConfigResult.class);
    }
    private DevpSysOpsTaskConfigResult addFail(DevpSysOpsTaskConfigAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除配置文件设置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskConfigResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskConfig/"+id;
        ResponseEntity<DevpSysOpsTaskConfigResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskConfigResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskConfigResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新配置文件设置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskConfigResult update(Long id, DevpSysOpsTaskConfigEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskConfig/"+id;
        ResponseEntity<DevpSysOpsTaskConfigResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskConfigResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskConfigResult updateFail(Long id, DevpSysOpsTaskConfigEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询配置文件设置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskConfigResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskConfig/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskConfigResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskConfigResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询配置文件设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskConfigPageResult list(PageSearchRequest<DevpSysOpsTaskConfigCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskConfig/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskConfigPageResult.class);
    }
    public DevpSysOpsTaskConfigPageResult listFail(PageSearchRequest<DevpSysOpsTaskConfigCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskConfigPageResult result = new DevpSysOpsTaskConfigPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskConfigResult errorResult(){
        DevpSysOpsTaskConfigResult result = new DevpSysOpsTaskConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
