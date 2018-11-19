package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import net.aicoder.speedcloud.client.app.result.CodeBaseInfoPageResult;
import net.aicoder.speedcloud.client.app.result.CodeBaseInfoResult;
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
 * 代码库详细信息客户端
 * @author icode
 */
@Service
public class CodeBaseInfoRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeBaseInfoRibbon.class);

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
     * 新增代码库详细信息
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public CodeBaseInfoResult add(CodeBaseInfoAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/codebaseinfo";
        return restTemplate.postForObject(url, addDto, CodeBaseInfoResult.class);
    }
    private CodeBaseInfoResult addFail(CodeBaseInfoAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除代码库详细信息
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public CodeBaseInfoResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/codebaseinfo/"+id;
        ResponseEntity<CodeBaseInfoResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<CodeBaseInfoResult>() {});
        return response.getBody();
    }
    private CodeBaseInfoResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新代码库详细信息
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public CodeBaseInfoResult update(Long id, CodeBaseInfoEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/codebaseinfo/"+id;
        ResponseEntity<CodeBaseInfoResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<CodeBaseInfoResult>() {});
        return response.getBody();
    }

    public CodeBaseInfoResult updateFail(Long id, CodeBaseInfoEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询代码库详细信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public CodeBaseInfoResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/codebaseinfo/"+id;
        return restTemplate.getForObject(url, CodeBaseInfoResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private CodeBaseInfoResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询代码库详细信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public CodeBaseInfoPageResult list(PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/codebaseinfo/list";
        return restTemplate.postForObject(url, pageSearchRequest, CodeBaseInfoPageResult.class);
    }
    public CodeBaseInfoPageResult listFail(PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        CodeBaseInfoPageResult result = new CodeBaseInfoPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private CodeBaseInfoResult errorResult(){
        CodeBaseInfoResult result = new CodeBaseInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
