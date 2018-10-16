package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCustomAddDto;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeEditDto;
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
public class PipelineExecNodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeRibbon.class);

    private String host = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE";

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
    @HystrixCommand(fallbackMethod = "customFail")
    public PipelineExecNodeResult custom(PipelineExecNodeCustomAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode/custom";
        return restTemplate.postForObject(url, addDto, PipelineExecNodeResult.class);
    }
    private PipelineExecNodeResult customFail(PipelineExecNodeCustomAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

    /**
     * 新增运行实例节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineExecNodeResult add(PipelineExecNodeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode";
        return restTemplate.postForObject(url, addDto, PipelineExecNodeResult.class);
    }
    private PipelineExecNodeResult addFail(PipelineExecNodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecNodeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode/"+id;
        ResponseEntity<PipelineExecNodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecNodeResult>() {});
        return response.getBody();
    }
    private PipelineExecNodeResult deleteFail(Long id, Throwable throwable) {

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
    public PipelineExecNodeResult update(Long id, PipelineExecNodeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode/"+id;
        ResponseEntity<PipelineExecNodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecNodeResult>() {});
        return response.getBody();
    }

    public PipelineExecNodeResult updateFail(Long id, PipelineExecNodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecNodeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode/"+id;
        return restTemplate.getForObject(url, PipelineExecNodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecNodeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecNodePageResult list(PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnode/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecNodePageResult.class);
    }
    public PipelineExecNodePageResult listFail(PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecNodePageResult result = new PipelineExecNodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecNodeResult errorResult(){
        PipelineExecNodeResult result = new PipelineExecNodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
