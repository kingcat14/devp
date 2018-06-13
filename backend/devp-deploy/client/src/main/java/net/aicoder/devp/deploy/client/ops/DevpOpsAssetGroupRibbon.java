package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetGroupPageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetGroupResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupEditDto;
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
 * 资产分组客户端
 * @author icode
 */
@Service
public class DevpOpsAssetGroupRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetGroupRibbon.class);

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
     * 新增资产分组
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsAssetGroupResult add(DevpOpsAssetGroupAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsAssetGroup";
        return restTemplate.postForObject(url, addDto, DevpOpsAssetGroupResult.class);
    }
    private DevpOpsAssetGroupResult addFail(DevpOpsAssetGroupAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetGroupResult result = new DevpOpsAssetGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除资产分组
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsAssetGroupResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetGroup/"+id;
        ResponseEntity<DevpOpsAssetGroupResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsAssetGroupResult>() {});
        return response.getBody();
    }
    private DevpOpsAssetGroupResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetGroupResult result = new DevpOpsAssetGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新资产分组
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsAssetGroupResult update(Long id, DevpOpsAssetGroupEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsAssetGroup/"+id;
        ResponseEntity<DevpOpsAssetGroupResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsAssetGroupResult>() {});
        return response.getBody();
    }

    public DevpOpsAssetGroupResult updateFail(Long id, DevpOpsAssetGroupEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetGroupResult result = new DevpOpsAssetGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询资产分组
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsAssetGroupResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetGroup/"+id;
        return restTemplate.getForObject(url, DevpOpsAssetGroupResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsAssetGroupResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetGroupResult result = new DevpOpsAssetGroupResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询资产分组列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsAssetGroupPageResult list(PageSearchRequest<DevpOpsAssetGroupCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsAssetGroup/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsAssetGroupPageResult.class);
    }
    public DevpOpsAssetGroupPageResult listFail(PageSearchRequest<DevpOpsAssetGroupCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetGroupPageResult result = new DevpOpsAssetGroupPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
