package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskBaselinePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskBaselineResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineEditDto;
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
 * 基线设置客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskBaselineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskBaselineRibbon.class);

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
     * 新增基线设置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskBaselineResult add(DevpSysOpsTaskBaselineAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskBaseline";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskBaselineResult.class);
    }
    private DevpSysOpsTaskBaselineResult addFail(DevpSysOpsTaskBaselineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除基线设置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskBaselineResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskBaseline/"+id;
        ResponseEntity<DevpSysOpsTaskBaselineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskBaselineResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskBaselineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新基线设置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskBaselineResult update(Long id, DevpSysOpsTaskBaselineEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskBaseline/"+id;
        ResponseEntity<DevpSysOpsTaskBaselineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskBaselineResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskBaselineResult updateFail(Long id, DevpSysOpsTaskBaselineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询基线设置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskBaselineResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskBaseline/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskBaselineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskBaselineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询基线设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskBaselinePageResult list(PageSearchRequest<DevpSysOpsTaskBaselineCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskBaseline/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskBaselinePageResult.class);
    }
    public DevpSysOpsTaskBaselinePageResult listFail(PageSearchRequest<DevpSysOpsTaskBaselineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskBaselinePageResult result = new DevpSysOpsTaskBaselinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskBaselineResult errorResult(){
        DevpSysOpsTaskBaselineResult result = new DevpSysOpsTaskBaselineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
