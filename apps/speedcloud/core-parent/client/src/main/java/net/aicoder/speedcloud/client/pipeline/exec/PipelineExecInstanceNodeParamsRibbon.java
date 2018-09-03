package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamsPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamsResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsEditDto;
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
 * 运行实例节点参数客户端
 * @author icode
 */
@Service
public class PipelineExecInstanceNodeParamsRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamsRibbon.class);

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
     * 新增运行实例节点参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineExecInstanceNodeParamsResult add(PipelineExecInstanceNodeParamsAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparams";
        return restTemplate.postForObject(url, addDto, PipelineExecInstanceNodeParamsResult.class);
    }
    private PipelineExecInstanceNodeParamsResult addFail(PipelineExecInstanceNodeParamsAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecInstanceNodeParamsResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparams/"+id;
        ResponseEntity<PipelineExecInstanceNodeParamsResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecInstanceNodeParamsResult>() {});
        return response.getBody();
    }
    private PipelineExecInstanceNodeParamsResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行实例节点参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineExecInstanceNodeParamsResult update(Long id, PipelineExecInstanceNodeParamsEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparams/"+id;
        ResponseEntity<PipelineExecInstanceNodeParamsResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecInstanceNodeParamsResult>() {});
        return response.getBody();
    }

    public PipelineExecInstanceNodeParamsResult updateFail(Long id, PipelineExecInstanceNodeParamsEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecInstanceNodeParamsResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparams/"+id;
        return restTemplate.getForObject(url, PipelineExecInstanceNodeParamsResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecInstanceNodeParamsResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecInstanceNodeParamsPageResult list(PageSearchRequest<PipelineExecInstanceNodeParamsCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparams/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecInstanceNodeParamsPageResult.class);
    }
    public PipelineExecInstanceNodeParamsPageResult listFail(PageSearchRequest<PipelineExecInstanceNodeParamsCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecInstanceNodeParamsPageResult result = new PipelineExecInstanceNodeParamsPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecInstanceNodeParamsResult errorResult(){
        PipelineExecInstanceNodeParamsResult result = new PipelineExecInstanceNodeParamsResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
