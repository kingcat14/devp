package net.aicoder.devp.client.publish;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskCompilePageResult;
import net.aicoder.devp.client.publish.result.DevpSysOpsTaskCompileResult;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
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
 * 编译设置客户端
 * @author icode
 */
@Service
public class DevpSysOpsTaskCompileRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskCompileRibbon.class);

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
     * 新增编译设置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysOpsTaskCompileResult add(DevpSysOpsTaskCompileAddDto addDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskCompile";
        return restTemplate.postForObject(url, addDto, DevpSysOpsTaskCompileResult.class);
    }
    private DevpSysOpsTaskCompileResult addFail(DevpSysOpsTaskCompileAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除编译设置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysOpsTaskCompileResult delete(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskCompile/"+id;
        ResponseEntity<DevpSysOpsTaskCompileResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysOpsTaskCompileResult>() {});
        return response.getBody();
    }
    private DevpSysOpsTaskCompileResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新编译设置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysOpsTaskCompileResult update(Long id, DevpSysOpsTaskCompileEditDto editDto) {
        String url = "http://"+host+"/publish/devpSysOpsTaskCompile/"+id;
        ResponseEntity<DevpSysOpsTaskCompileResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysOpsTaskCompileResult>() {});
        return response.getBody();
    }

    public DevpSysOpsTaskCompileResult updateFail(Long id, DevpSysOpsTaskCompileEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询编译设置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysOpsTaskCompileResult get(Long id) {
        String url = "http://"+host+"/publish/devpSysOpsTaskCompile/"+id;
        return restTemplate.getForObject(url, DevpSysOpsTaskCompileResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysOpsTaskCompileResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询编译设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysOpsTaskCompilePageResult list(PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest) {
        String url = "http://"+host+"/publish/devpSysOpsTaskCompile/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysOpsTaskCompilePageResult.class);
    }
    public DevpSysOpsTaskCompilePageResult listFail(PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysOpsTaskCompilePageResult result = new DevpSysOpsTaskCompilePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevpSysOpsTaskCompileResult errorResult(){
        DevpSysOpsTaskCompileResult result = new DevpSysOpsTaskCompileResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }
    

}
