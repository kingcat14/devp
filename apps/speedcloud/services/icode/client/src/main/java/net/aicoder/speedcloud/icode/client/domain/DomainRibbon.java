package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.DomainPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.DomainResult;
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
 * 领域客户端
 * @author icode
 */
@Service
public class DomainRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainRibbon.class);

    private String host = "SPEEDCLOUD-ICODE-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增领域
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DomainResult add(DomainAddDto addDto) {
        String url = "http://"+host+"/icode/domain/domain";
        return restTemplate.postForObject(url, addDto, DomainResult.class);
    }
    private DomainResult addFail(DomainAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DomainResult delete(String id) {
        String url = "http://"+host+"/icode/domain/domain/"+id;
        ResponseEntity<DomainResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DomainResult>() {});
        return response.getBody();
    }
    private DomainResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DomainResult update(String id, DomainEditDto editDto) {
        String url = "http://"+host+"/icode/domain/domain/"+id;
        ResponseEntity<DomainResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DomainResult>() {});
        return response.getBody();
    }

    /**
     * 复制
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFail")
    public DomainResult copy(String id) {
        String url = "http://"+host+"/icode/domain/domain/"+id+"/copy";
        ResponseEntity<DomainResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(null), new ParameterizedTypeReference<DomainResult>() {});
        return response.getBody();
    }

    public DomainResult updateFail(String id, DomainEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DomainResult get(String id) {
        String url = "http://"+host+"/icode/domain/domain/"+id;
        return restTemplate.getForObject(url, DomainResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DomainResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DomainPageResult list(PageSearchRequest<DomainCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/domain/list";
        return restTemplate.postForObject(url, pageSearchRequest, DomainPageResult.class);
    }
    public DomainPageResult listFail(PageSearchRequest<DomainCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DomainPageResult result = new DomainPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DomainResult errorResult(){
        DomainResult result = new DomainResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
