package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipePlanPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipePlanResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
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
 * 产品运维流水线执行计划客户端
 * @author icode
 */
@Service
public class DevpSysOpsPipePlanRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipePlanRibbon.class);

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
     * 新增产品运维流水线执行计划
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsPipePlanResult add(DevpSysOpsPipePlanAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipePlan";
        return restTemplate.postForObject(url, addDto, DevpSysOpsPipePlanResult.class);
    }
    private DevpSysOpsPipePlanResult addFail(DevpSysOpsPipePlanAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除产品运维流水线执行计划
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsPipePlanResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipePlan/"+id;
        ResponseEntity<DevpSysOpsPipePlanResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsPipePlanResult>() {});
        return response.getBody();
    }
    private DevpSysOpsPipePlanResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新产品运维流水线执行计划
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsPipePlanResult update(Long id, DevpSysOpsPipePlanEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipePlan/"+id;
        ResponseEntity<DevpSysOpsPipePlanResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsPipePlanResult>() {});
        return response.getBody();
    }

    public DevpSysOpsPipePlanResult updateFail(Long id, DevpSysOpsPipePlanEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询产品运维流水线执行计划
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsPipePlanResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipePlan/"+id;
        return restTemplate.getForObject(url, DevpSysOpsPipePlanResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsPipePlanResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询产品运维流水线执行计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsPipePlanPageResult list(PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsPipePlan/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsPipePlanPageResult.class);
    }
    public DevpSysOpsPipePlanPageResult listFail(PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsPipePlanPageResult result = new DevpSysOpsPipePlanPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsPipePlanResult errorResult(){
        DevpSysOpsPipePlanResult result = new DevpSysOpsPipePlanResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
