package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.result.CodeRepertoryPageResult;
import net.aicoder.speedcloud.client.app.result.CodeRepertoryResult;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryEditDto;
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
 * 代码库客户端
 * @author icode
 */
@Service
public class CodeRepertoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepertoryRibbon.class);

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
     * 新增代码库
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public CodeRepertoryResult add(CodeRepertoryAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/coderepertory";
        return restTemplate.postForObject(url, addDto, CodeRepertoryResult.class);
    }
    private CodeRepertoryResult addFail(CodeRepertoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除代码库
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public CodeRepertoryResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/coderepertory/"+id;
        ResponseEntity<CodeRepertoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<CodeRepertoryResult>() {});
        return response.getBody();
    }
    private CodeRepertoryResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新代码库
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public CodeRepertoryResult update(Long id, CodeRepertoryEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/coderepertory/"+id;
        ResponseEntity<CodeRepertoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<CodeRepertoryResult>() {});
        return response.getBody();
    }

    public CodeRepertoryResult updateFail(Long id, CodeRepertoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询代码库
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public CodeRepertoryResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/coderepertory/"+id;
        return restTemplate.getForObject(url, CodeRepertoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private CodeRepertoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询代码库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public CodeRepertoryPageResult list(PageSearchRequest<CodeRepertoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/coderepertory/list";
        return restTemplate.postForObject(url, pageSearchRequest, CodeRepertoryPageResult.class);
    }
    public CodeRepertoryPageResult listFail(PageSearchRequest<CodeRepertoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        CodeRepertoryPageResult result = new CodeRepertoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private CodeRepertoryResult errorResult(){
        CodeRepertoryResult result = new CodeRepertoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
