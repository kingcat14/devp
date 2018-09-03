package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamEditDto;
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
public class PipelineExecInstanceNodeParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamRibbon.class);

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
    public PipelineExecInstanceNodeParamResult add(PipelineExecInstanceNodeParamAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparam";
        return restTemplate.postForObject(url, addDto, PipelineExecInstanceNodeParamResult.class);
    }
    private PipelineExecInstanceNodeParamResult addFail(PipelineExecInstanceNodeParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecInstanceNodeParamResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparam/"+id;
        ResponseEntity<PipelineExecInstanceNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecInstanceNodeParamResult>() {});
        return response.getBody();
    }
    private PipelineExecInstanceNodeParamResult deleteFail(Long id, Throwable throwable) {

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
    public PipelineExecInstanceNodeParamResult update(Long id, PipelineExecInstanceNodeParamEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparam/"+id;
        ResponseEntity<PipelineExecInstanceNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecInstanceNodeParamResult>() {});
        return response.getBody();
    }

    public PipelineExecInstanceNodeParamResult updateFail(Long id, PipelineExecInstanceNodeParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecInstanceNodeParamResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparam/"+id;
        return restTemplate.getForObject(url, PipelineExecInstanceNodeParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecInstanceNodeParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecInstanceNodeParamPageResult list(PageSearchRequest<PipelineExecInstanceNodeParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenodeparam/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecInstanceNodeParamPageResult.class);
    }
    public PipelineExecInstanceNodeParamPageResult listFail(PageSearchRequest<PipelineExecInstanceNodeParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecInstanceNodeParamPageResult result = new PipelineExecInstanceNodeParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecInstanceNodeParamResult errorResult(){
        PipelineExecInstanceNodeParamResult result = new PipelineExecInstanceNodeParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
