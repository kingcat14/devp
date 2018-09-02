package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecEditDto;
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
 * 运行实例客户端
 * @author icode
 */
@Service
public class ExecRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecRibbon.class);

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
     * 新增运行实例
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ExecResult add(ExecAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/exec";
        return restTemplate.postForObject(url, addDto, ExecResult.class);
    }
    private ExecResult addFail(ExecAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ExecResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/exec/"+id;
        ResponseEntity<ExecResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ExecResult>() {});
        return response.getBody();
    }
    private ExecResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行实例
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ExecResult update(Long id, ExecEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/exec/"+id;
        ResponseEntity<ExecResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ExecResult>() {});
        return response.getBody();
    }

    public ExecResult updateFail(Long id, ExecEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ExecResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/exec/"+id;
        return restTemplate.getForObject(url, ExecResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ExecResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ExecPageResult list(PageSearchRequest<ExecCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/exec/list";
        return restTemplate.postForObject(url, pageSearchRequest, ExecPageResult.class);
    }
    public ExecPageResult listFail(PageSearchRequest<ExecCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ExecPageResult result = new ExecPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ExecResult errorResult(){
        ExecResult result = new ExecResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
