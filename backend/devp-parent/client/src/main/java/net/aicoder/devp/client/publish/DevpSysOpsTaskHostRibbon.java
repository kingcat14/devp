package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskHostPageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskHostResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostEditDto;
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
 * 部署主机节点客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskHostRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskHostRibbon.class);

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
     * 新增部署主机节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskHostResult add(DevpSysOpsTaskHostAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskHost";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskHostResult.class);
    }
    private DevpSysOpsTaskHostResult addFail(DevpSysOpsTaskHostAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署主机节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskHostResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskHost/"+id;
        ResponseEntity<DevpSysOpsTaskHostResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskHostResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskHostResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署主机节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskHostResult update(Long id, DevpSysOpsTaskHostEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskHost/"+id;
        ResponseEntity<DevpSysOpsTaskHostResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskHostResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskHostResult updateFail(Long id, DevpSysOpsTaskHostEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskHostResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskHost/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskHostResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskHostResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskHostPageResult list(PageSearchRequest<DevpSysOpsTaskHostCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskHost/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskHostPageResult.class);
    }
    public DevpSysOpsTaskHostPageResult listFail(PageSearchRequest<DevpSysOpsTaskHostCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskHostPageResult result = new DevpSysOpsTaskHostPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskHostResult errorResult(){
        DevpSysOpsTaskHostResult result = new DevpSysOpsTaskHostResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
