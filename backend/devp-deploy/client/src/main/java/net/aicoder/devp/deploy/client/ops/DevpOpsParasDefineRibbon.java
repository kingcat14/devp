package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsParasDefinePageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsParasDefineResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineEditDto;
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
 * 运维元素扩充信息客户端
 * @author icode
 */
@Service
public class DevpOpsParasDefineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsParasDefineRibbon.class);

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
     * 新增运维元素扩充信息
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsParasDefineResult add(DevpOpsParasDefineAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsParasDefine";
        return restTemplate.postForObject(url, addDto, DevpOpsParasDefineResult.class);
    }
    private DevpOpsParasDefineResult addFail(DevpOpsParasDefineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsParasDefineResult result = new DevpOpsParasDefineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除运维元素扩充信息
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsParasDefineResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsParasDefine/"+id;
        ResponseEntity<DevpOpsParasDefineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsParasDefineResult>() {});
        return response.getBody();
    }
    private DevpOpsParasDefineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsParasDefineResult result = new DevpOpsParasDefineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新运维元素扩充信息
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsParasDefineResult update(Long id, DevpOpsParasDefineEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsParasDefine/"+id;
        ResponseEntity<DevpOpsParasDefineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsParasDefineResult>() {});
        return response.getBody();
    }

    public DevpOpsParasDefineResult updateFail(Long id, DevpOpsParasDefineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsParasDefineResult result = new DevpOpsParasDefineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询运维元素扩充信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsParasDefineResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsParasDefine/"+id;
        return restTemplate.getForObject(url, DevpOpsParasDefineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsParasDefineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsParasDefineResult result = new DevpOpsParasDefineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询运维元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsParasDefinePageResult list(PageSearchRequest<DevpOpsParasDefineCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsParasDefine/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsParasDefinePageResult.class);
    }
    public DevpOpsParasDefinePageResult listFail(PageSearchRequest<DevpOpsParasDefineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsParasDefinePageResult result = new DevpOpsParasDefinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
