package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsCiGroupPageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsCiGroupResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupEditDto;
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
 * 资产项目分组客户端
 * @author icode
 */
@Service
public class DevpOpsCiGroupRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsCiGroupRibbon.class);

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
     * 新增资产项目分组
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsCiGroupResult add(DevpOpsCiGroupAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsCiGroup";
        return restTemplate.postForObject(url, addDto, DevpOpsCiGroupResult.class);
    }
    private DevpOpsCiGroupResult addFail(DevpOpsCiGroupAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsCiGroupResult result = new DevpOpsCiGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除资产项目分组
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsCiGroupResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsCiGroup/"+id;
        ResponseEntity<DevpOpsCiGroupResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsCiGroupResult>() {});
        return response.getBody();
    }
    private DevpOpsCiGroupResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsCiGroupResult result = new DevpOpsCiGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新资产项目分组
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsCiGroupResult update(Long id, DevpOpsCiGroupEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsCiGroup/"+id;
        ResponseEntity<DevpOpsCiGroupResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsCiGroupResult>() {});
        return response.getBody();
    }

    public DevpOpsCiGroupResult updateFail(Long id, DevpOpsCiGroupEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsCiGroupResult result = new DevpOpsCiGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询资产项目分组
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsCiGroupResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsCiGroup/"+id;
        return restTemplate.getForObject(url, DevpOpsCiGroupResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsCiGroupResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsCiGroupResult result = new DevpOpsCiGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询资产项目分组列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsCiGroupPageResult list(PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsCiGroup/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsCiGroupPageResult.class);
    }
    public DevpOpsCiGroupPageResult listFail(PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsCiGroupPageResult result = new DevpOpsCiGroupPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
