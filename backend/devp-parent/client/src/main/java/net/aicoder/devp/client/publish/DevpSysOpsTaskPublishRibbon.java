package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskPublishPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskPublishResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
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
 * 发布设置客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskPublishRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskPublishRibbon.class);

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
     * 新增发布设置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskPublishResult add(DevpSysOpsTaskPublishAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskPublish";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskPublishResult.class);
    }
    private DevpSysOpsTaskPublishResult addFail(DevpSysOpsTaskPublishAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除发布设置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskPublishResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskPublish/"+id;
        ResponseEntity<DevpSysOpsTaskPublishResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskPublishResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskPublishResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新发布设置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskPublishResult update(Long id, DevpSysOpsTaskPublishEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskPublish/"+id;
        ResponseEntity<DevpSysOpsTaskPublishResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskPublishResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskPublishResult updateFail(Long id, DevpSysOpsTaskPublishEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询发布设置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskPublishResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskPublish/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskPublishResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskPublishResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询发布设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskPublishPageResult list(PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskPublish/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskPublishPageResult.class);
    }
    public DevpSysOpsTaskPublishPageResult listFail(PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskPublishPageResult result = new DevpSysOpsTaskPublishPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskPublishResult errorResult(){
        DevpSysOpsTaskPublishResult result = new DevpSysOpsTaskPublishResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
