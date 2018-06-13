package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAttachmentPageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAttachmentResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentEditDto;
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
 * 附件定义客户端
 * @author icode
 */
@Service
public class DevpOpsAttachmentRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAttachmentRibbon.class);

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
     * 新增附件定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsAttachmentResult add(DevpOpsAttachmentAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsAttachment";
        return restTemplate.postForObject(url, addDto, DevpOpsAttachmentResult.class);
    }
    private DevpOpsAttachmentResult addFail(DevpOpsAttachmentAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAttachmentResult result = new DevpOpsAttachmentResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除附件定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsAttachmentResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsAttachment/"+id;
        ResponseEntity<DevpOpsAttachmentResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsAttachmentResult>() {});
        return response.getBody();
    }
    private DevpOpsAttachmentResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAttachmentResult result = new DevpOpsAttachmentResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新附件定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsAttachmentResult update(Long id, DevpOpsAttachmentEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsAttachment/"+id;
        ResponseEntity<DevpOpsAttachmentResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsAttachmentResult>() {});
        return response.getBody();
    }

    public DevpOpsAttachmentResult updateFail(Long id, DevpOpsAttachmentEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAttachmentResult result = new DevpOpsAttachmentResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询附件定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsAttachmentResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsAttachment/"+id;
        return restTemplate.getForObject(url, DevpOpsAttachmentResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsAttachmentResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAttachmentResult result = new DevpOpsAttachmentResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询附件定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsAttachmentPageResult list(PageSearchRequest<DevpOpsAttachmentCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsAttachment/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsAttachmentPageResult.class);
    }
    public DevpOpsAttachmentPageResult listFail(PageSearchRequest<DevpOpsAttachmentCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAttachmentPageResult result = new DevpOpsAttachmentPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
