package net.aicoder.devp.deploy.client.ops;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetCmdbPageResult;
import net.aicoder.devp.deploy.client.ops.result.DevpOpsAssetCmdbResult;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbEditDto;
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
 * 资产定义客户端
 * @author icode
 */
@Service
public class DevpOpsAssetCmdbRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbRibbon.class);

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
     * 新增资产定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpOpsAssetCmdbResult add(DevpOpsAssetCmdbAddDto addDto) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, DevpOpsAssetCmdbResult.class);
    }
    private DevpOpsAssetCmdbResult addFail(DevpOpsAssetCmdbAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetCmdbResult result = new DevpOpsAssetCmdbResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除资产定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpOpsAssetCmdbResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<DevpOpsAssetCmdbResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpOpsAssetCmdbResult>() {});
        return response.getBody();
    }
    private DevpOpsAssetCmdbResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetCmdbResult result = new DevpOpsAssetCmdbResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新资产定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpOpsAssetCmdbResult update(Long id, DevpOpsAssetCmdbEditDto editDto) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<DevpOpsAssetCmdbResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpOpsAssetCmdbResult>() {});
        return response.getBody();
    }

    public DevpOpsAssetCmdbResult updateFail(Long id, DevpOpsAssetCmdbEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetCmdbResult result = new DevpOpsAssetCmdbResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询资产定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpOpsAssetCmdbResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, DevpOpsAssetCmdbResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpOpsAssetCmdbResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetCmdbResult result = new DevpOpsAssetCmdbResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询资产定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpOpsAssetCmdbPageResult list(PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpOpsAssetCmdbPageResult.class);
    }
    public DevpOpsAssetCmdbPageResult listFail(PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpOpsAssetCmdbPageResult result = new DevpOpsAssetCmdbPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
