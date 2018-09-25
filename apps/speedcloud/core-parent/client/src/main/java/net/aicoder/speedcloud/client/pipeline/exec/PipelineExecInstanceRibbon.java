package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.*;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstancePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeParamResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeResult;
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
 * 运行计划客户端
 * @author icode
 */
@Service
public class PipelineExecInstanceRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceRibbon.class);

    private String host = "SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 自定义运行实例
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "customFail")
    public PipelineExecInstanceResult custom(PipelineExecNodeCustomAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance/custom";
        return restTemplate.postForObject(url, addDto, PipelineExecInstanceResult.class);
    }
    private PipelineExecInstanceResult customFail(PipelineExecNodeCustomAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

    /**
     * 新增运行计划
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineExecInstanceResult add(PipelineExecInstanceAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance";
        return restTemplate.postForObject(url, addDto, PipelineExecInstanceResult.class);
    }
    private PipelineExecInstanceResult addFail(PipelineExecInstanceAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行计划
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecInstanceResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance/"+id;
        ResponseEntity<PipelineExecInstanceResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecInstanceResult>() {});
        return response.getBody();
    }
    private PipelineExecInstanceResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行计划
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineExecInstanceResult update(Long id, PipelineExecInstanceEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance/"+id;
        ResponseEntity<PipelineExecInstanceResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecInstanceResult>() {});
        return response.getBody();
    }

    public PipelineExecInstanceResult updateFail(Long id, PipelineExecInstanceEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行计划
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecInstanceResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance/"+id;
        return restTemplate.getForObject(url, PipelineExecInstanceResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecInstanceResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecInstancePageResult list(PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstance/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecInstancePageResult.class);
    }
    public PipelineExecInstancePageResult listFail(PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecInstancePageResult result = new PipelineExecInstancePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecInstanceResult errorResult(){
        PipelineExecInstanceResult result = new PipelineExecInstanceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
