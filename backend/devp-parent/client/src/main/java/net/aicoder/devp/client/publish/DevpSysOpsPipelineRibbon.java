package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipelinePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipelineResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineEditDto;
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
 * 产品运维流水线客户端
 * @author icode
 */
@Service
public class DevpSysOpsPipelineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipelineRibbon.class);

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
     * 新增产品运维流水线
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsPipelineResult add(DevpSysOpsPipelineAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeline";
        return restTemplate.postForObject(url, addDto, DevpSysOpsPipelineResult.class);
    }
    private DevpSysOpsPipelineResult addFail(DevpSysOpsPipelineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除产品运维流水线
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsPipelineResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeline/"+id;
        ResponseEntity<DevpSysOpsPipelineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsPipelineResult>() {});
        return response.getBody();
    }
    private DevpSysOpsPipelineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新产品运维流水线
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsPipelineResult update(Long id, DevpSysOpsPipelineEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeline/"+id;
        ResponseEntity<DevpSysOpsPipelineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsPipelineResult>() {});
        return response.getBody();
    }

    public DevpSysOpsPipelineResult updateFail(Long id, DevpSysOpsPipelineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询产品运维流水线
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsPipelineResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeline/"+id;
        return restTemplate.getForObject(url, DevpSysOpsPipelineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsPipelineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询产品运维流水线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsPipelinePageResult list(PageSearchRequest<DevpSysOpsPipelineCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsPipeline/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsPipelinePageResult.class);
    }
    public DevpSysOpsPipelinePageResult listFail(PageSearchRequest<DevpSysOpsPipelineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsPipelinePageResult result = new DevpSysOpsPipelinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsPipelineResult errorResult(){
        DevpSysOpsPipelineResult result = new DevpSysOpsPipelineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
