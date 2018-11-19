package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogCondition;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeLogPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeLogResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 运行节点日志客户端
 * @author icode
 */
@Service
public class PipelineExecNodeLogRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeLogRibbon.class);

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
	 * 根据ID查询运行节点日志
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecNodeLogResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodelog/"+id;
        return restTemplate.getForObject(url, PipelineExecNodeLogResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecNodeLogResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行节点日志列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecNodeLogPageResult list(PageSearchRequest<PipelineExecNodeLogCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodelog/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecNodeLogPageResult.class);
    }
    public PipelineExecNodeLogPageResult listFail(PageSearchRequest<PipelineExecNodeLogCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecNodeLogPageResult result = new PipelineExecNodeLogPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecNodeLogResult errorResult(){
        PipelineExecNodeLogResult result = new PipelineExecNodeLogResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
