package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsIssuePageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsIssueResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueEditDto;
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
 * 问题记录客户端
 * @author icode
 */
@Service
public class DevpOpsIssueRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueRibbon.class);

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
     * 新增问题记录
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsIssueResult add(DevpOpsIssueAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsIssue";
        return restTemplate.postForObject(url, addDto, DevpOpsIssueResult.class);
    }
    private DevpOpsIssueResult addFail(DevpOpsIssueAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsIssueResult result = new DevpOpsIssueResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除问题记录
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsIssueResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsIssue/"+id;
        ResponseEntity<DevpOpsIssueResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsIssueResult>() {});
        return response.getBody();
    }
    private DevpOpsIssueResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsIssueResult result = new DevpOpsIssueResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新问题记录
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsIssueResult update(Long id, DevpOpsIssueEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsIssue/"+id;
        ResponseEntity<DevpOpsIssueResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsIssueResult>() {});
        return response.getBody();
    }

    public DevpOpsIssueResult updateFail(Long id, DevpOpsIssueEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsIssueResult result = new DevpOpsIssueResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询问题记录
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsIssueResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsIssue/"+id;
        return restTemplate.getForObject(url, DevpOpsIssueResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsIssueResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsIssueResult result = new DevpOpsIssueResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询问题记录列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsIssuePageResult list(PageSearchRequest<DevpOpsIssueCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsIssue/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsIssuePageResult.class);
    }
    public DevpOpsIssuePageResult listFail(PageSearchRequest<DevpOpsIssueCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsIssuePageResult result = new DevpOpsIssuePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
