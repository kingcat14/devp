package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.app.result.CodeRepositoryPageResult;
import net.aicoder.speedcloud.client.app.result.CodeRepositoryResult;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
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
public class CodeRepositoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryRibbon.class);

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
    public CodeRepositoryResult add(CodeRepositoryAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/coderepository";
        return restTemplate.postForObject(url, addDto, CodeRepositoryResult.class);
    }
    private CodeRepositoryResult addFail(CodeRepositoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除代码库
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public CodeRepositoryResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/coderepository/"+id;
        ResponseEntity<CodeRepositoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<CodeRepositoryResult>() {});
        return response.getBody();
    }
    private CodeRepositoryResult deleteFail(Long id, Throwable throwable) {

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
    public CodeRepositoryResult update(Long id, CodeRepositoryEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/coderepository/"+id;
        ResponseEntity<CodeRepositoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<CodeRepositoryResult>() {});
        return response.getBody();
    }

    public CodeRepositoryResult updateFail(Long id, CodeRepositoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询代码库
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public CodeRepositoryResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/coderepository/"+id;
        return restTemplate.getForObject(url, CodeRepositoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private CodeRepositoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询代码库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public CodeRepositoryPageResult list(PageSearchRequest<CodeRepositoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/coderepository/list";
        return restTemplate.postForObject(url, pageSearchRequest, CodeRepositoryPageResult.class);
    }
    public CodeRepositoryPageResult listFail(PageSearchRequest<CodeRepositoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        CodeRepositoryPageResult result = new CodeRepositoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private CodeRepositoryResult errorResult(){
        CodeRepositoryResult result = new CodeRepositoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
