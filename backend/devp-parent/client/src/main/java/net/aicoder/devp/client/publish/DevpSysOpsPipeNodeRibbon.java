package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeNodePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsPipeNodeResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
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
 * 流水线执行节点客户端
 * @author icode
 */
@Service
public class DevpSysOpsPipeNodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeNodeRibbon.class);

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
     * 新增流水线执行节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsPipeNodeResult add(DevpSysOpsPipeNodeAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeNode";
        return restTemplate.postForObject(url, addDto, DevpSysOpsPipeNodeResult.class);
    }
    private DevpSysOpsPipeNodeResult addFail(DevpSysOpsPipeNodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除流水线执行节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsPipeNodeResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeNode/"+id;
        ResponseEntity<DevpSysOpsPipeNodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsPipeNodeResult>() {});
        return response.getBody();
    }
    private DevpSysOpsPipeNodeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新流水线执行节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsPipeNodeResult update(Long id, DevpSysOpsPipeNodeEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsPipeNode/"+id;
        ResponseEntity<DevpSysOpsPipeNodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsPipeNodeResult>() {});
        return response.getBody();
    }

    public DevpSysOpsPipeNodeResult updateFail(Long id, DevpSysOpsPipeNodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询流水线执行节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsPipeNodeResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsPipeNode/"+id;
        return restTemplate.getForObject(url, DevpSysOpsPipeNodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsPipeNodeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询流水线执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsPipeNodePageResult list(PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsPipeNode/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsPipeNodePageResult.class);
    }
    public DevpSysOpsPipeNodePageResult listFail(PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsPipeNodePageResult result = new DevpSysOpsPipeNodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsPipeNodeResult errorResult(){
        DevpSysOpsPipeNodeResult result = new DevpSysOpsPipeNodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
