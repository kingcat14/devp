package net.aicoder.speedcloud.client.pipeline.command;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.command.result.PipelineJobCommandPageResult;
import net.aicoder.speedcloud.client.pipeline.command.result.PipelineJobCommandResult;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandAddDto;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandCondition;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandEditDto;
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
 * 创建Job指令客户端
 * @author icode
 */
@Service
public class PipelineJobCommandRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineJobCommandRibbon.class);

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
     * 新增创建Job指令
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineJobCommandResult add(PipelineJobCommandAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/command/pipelinejobcommand";
        return restTemplate.postForObject(url, addDto, PipelineJobCommandResult.class);
    }
    private PipelineJobCommandResult addFail(PipelineJobCommandAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除创建Job指令
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineJobCommandResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/command/pipelinejobcommand/"+id;
        ResponseEntity<PipelineJobCommandResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineJobCommandResult>() {});
        return response.getBody();
    }
    private PipelineJobCommandResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新创建Job指令
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineJobCommandResult update(Long id, PipelineJobCommandEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/command/pipelinejobcommand/"+id;
        ResponseEntity<PipelineJobCommandResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineJobCommandResult>() {});
        return response.getBody();
    }

    public PipelineJobCommandResult updateFail(Long id, PipelineJobCommandEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询创建Job指令
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineJobCommandResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/command/pipelinejobcommand/"+id;
        return restTemplate.getForObject(url, PipelineJobCommandResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineJobCommandResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询创建Job指令列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineJobCommandPageResult list(PageSearchRequest<PipelineJobCommandCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/command/pipelinejobcommand/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineJobCommandPageResult.class);
    }
    public PipelineJobCommandPageResult listFail(PageSearchRequest<PipelineJobCommandCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineJobCommandPageResult result = new PipelineJobCommandPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineJobCommandResult errorResult(){
        PipelineJobCommandResult result = new PipelineJobCommandResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
