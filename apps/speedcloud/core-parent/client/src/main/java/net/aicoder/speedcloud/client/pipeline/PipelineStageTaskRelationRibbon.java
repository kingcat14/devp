package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageTaskRelationPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageTaskRelationResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationEditDto;
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
 * 阶段任务关联客户端
 * @author icode
 */
@Service
public class PipelineStageTaskRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageTaskRelationRibbon.class);

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
     * 新增阶段任务关联
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineStageTaskRelationResult add(PipelineStageTaskRelationAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagetaskrelation";
        return restTemplate.postForObject(url, addDto, PipelineStageTaskRelationResult.class);
    }
    private PipelineStageTaskRelationResult addFail(PipelineStageTaskRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除阶段任务关联
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineStageTaskRelationResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagetaskrelation/"+id;
        ResponseEntity<PipelineStageTaskRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineStageTaskRelationResult>() {});
        return response.getBody();
    }
    private PipelineStageTaskRelationResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新阶段任务关联
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineStageTaskRelationResult update(Long id, PipelineStageTaskRelationEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagetaskrelation/"+id;
        ResponseEntity<PipelineStageTaskRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineStageTaskRelationResult>() {});
        return response.getBody();
    }

    public PipelineStageTaskRelationResult updateFail(Long id, PipelineStageTaskRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询阶段任务关联
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineStageTaskRelationResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagetaskrelation/"+id;
        return restTemplate.getForObject(url, PipelineStageTaskRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineStageTaskRelationResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询阶段任务关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineStageTaskRelationPageResult list(PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagetaskrelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineStageTaskRelationPageResult.class);
    }
    public PipelineStageTaskRelationPageResult listFail(PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineStageTaskRelationPageResult result = new PipelineStageTaskRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineStageTaskRelationResult errorResult(){
        PipelineStageTaskRelationResult result = new PipelineStageTaskRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
