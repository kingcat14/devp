package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeCmpPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeCmpResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpEditDto;
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
 * 产品运维流水线对应的组件客户端
 * @author icode
 */
@Service
public class DevpSysOpsPipeCmpRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeCmpRibbon.class);

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
     * 新增产品运维流水线对应的组件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsPipeCmpResult add(DevpSysOpsPipeCmpAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeCmp";
        return restTemplate.postForObject(url, addDto, DevpSysOpsPipeCmpResult.class);
    }
    private DevpSysOpsPipeCmpResult addFail(DevpSysOpsPipeCmpAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除产品运维流水线对应的组件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsPipeCmpResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeCmp/"+id;
        ResponseEntity<DevpSysOpsPipeCmpResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsPipeCmpResult>() {});
        return response.getBody();
    }
    private DevpSysOpsPipeCmpResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新产品运维流水线对应的组件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsPipeCmpResult update(Long id, DevpSysOpsPipeCmpEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeCmp/"+id;
        ResponseEntity<DevpSysOpsPipeCmpResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsPipeCmpResult>() {});
        return response.getBody();
    }

    public DevpSysOpsPipeCmpResult updateFail(Long id, DevpSysOpsPipeCmpEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询产品运维流水线对应的组件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsPipeCmpResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeCmp/"+id;
        return restTemplate.getForObject(url, DevpSysOpsPipeCmpResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsPipeCmpResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询产品运维流水线对应的组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsPipeCmpPageResult list(PageSearchRequest<DevpSysOpsPipeCmpCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsPipeCmp/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsPipeCmpPageResult.class);
    }
    public DevpSysOpsPipeCmpPageResult listFail(PageSearchRequest<DevpSysOpsPipeCmpCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsPipeCmpPageResult result = new DevpSysOpsPipeCmpPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsPipeCmpResult errorResult(){
        DevpSysOpsPipeCmpResult result = new DevpSysOpsPipeCmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
