package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeEditDto;
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
 * 运行实例节点客户端
 * @author icode
 */
@Service
public class PipelineExecInstanceNodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeRibbon.class);

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
     * 新增运行实例节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineExecInstanceNodeResult add(PipelineExecInstanceNodeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenode";
        return restTemplate.postForObject(url, addDto, PipelineExecInstanceNodeResult.class);
    }
    private PipelineExecInstanceNodeResult addFail(PipelineExecInstanceNodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecInstanceNodeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenode/"+id;
        ResponseEntity<PipelineExecInstanceNodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecInstanceNodeResult>() {});
        return response.getBody();
    }
    private PipelineExecInstanceNodeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行实例节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineExecInstanceNodeResult update(Long id, PipelineExecInstanceNodeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenode/"+id;
        ResponseEntity<PipelineExecInstanceNodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecInstanceNodeResult>() {});
        return response.getBody();
    }

    public PipelineExecInstanceNodeResult updateFail(Long id, PipelineExecInstanceNodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecInstanceNodeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenode/"+id;
        return restTemplate.getForObject(url, PipelineExecInstanceNodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecInstanceNodeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecInstanceNodePageResult list(PageSearchRequest<PipelineExecInstanceNodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecinstancenode/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecInstanceNodePageResult.class);
    }
    public PipelineExecInstanceNodePageResult listFail(PageSearchRequest<PipelineExecInstanceNodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecInstanceNodePageResult result = new PipelineExecInstanceNodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecInstanceNodeResult errorResult(){
        PipelineExecInstanceNodeResult result = new PipelineExecInstanceNodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
