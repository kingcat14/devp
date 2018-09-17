package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeEditDto;
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
 * 阶段执行节点客户端
 * @author icode
 */
@Service
public class PipelineStageNodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeRibbon.class);

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
     * 新增阶段执行节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineStageNodeResult add(PipelineStageNodeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenode";
        return restTemplate.postForObject(url, addDto, PipelineStageNodeResult.class);
    }
    private PipelineStageNodeResult addFail(PipelineStageNodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除阶段执行节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineStageNodeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenode/"+id;
        ResponseEntity<PipelineStageNodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineStageNodeResult>() {});
        return response.getBody();
    }
    private PipelineStageNodeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新阶段执行节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineStageNodeResult update(Long id, PipelineStageNodeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenode/"+id;
        ResponseEntity<PipelineStageNodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineStageNodeResult>() {});
        return response.getBody();
    }

    public PipelineStageNodeResult updateFail(Long id, PipelineStageNodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询阶段执行节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineStageNodeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenode/"+id;
        return restTemplate.getForObject(url, PipelineStageNodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineStageNodeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询阶段执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineStageNodePageResult list(PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenode/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineStageNodePageResult.class);
    }
    public PipelineStageNodePageResult listFail(PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineStageNodePageResult result = new PipelineStageNodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineStageNodeResult errorResult(){
        PipelineStageNodeResult result = new PipelineStageNodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
