package net.aicoder.speedcloud.icode.client.tplfile;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetEditDto;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplSetPageResult;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplSetResult;
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
 * 公共代码模板集合客户端
 * @author icode
 */
@Service
public class TplSetRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplSetRibbon.class);

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
     * 新增公共代码模板集合
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public TplSetResult add(TplSetAddDto addDto) {
        String url = "http://"+host+"/icode/tplfile/tplset";
        return restTemplate.postForObject(url, addDto, TplSetResult.class);
    }
    private TplSetResult addFail(TplSetAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除公共代码模板集合
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public TplSetResult delete(String id) {
        String url = "http://"+host+"/icode/tplfile/tplset/"+id;
        ResponseEntity<TplSetResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<TplSetResult>() {});
        return response.getBody();
    }
    private TplSetResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新公共代码模板集合
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public TplSetResult update(String id, TplSetEditDto editDto) {
        String url = "http://"+host+"/icode/tplfile/tplset/"+id;
        ResponseEntity<TplSetResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<TplSetResult>() {});
        return response.getBody();
    }

    public TplSetResult updateFail(String id, TplSetEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

    /**
     * 根据ID复制公共代码模板集合
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFail")
    public TplSetResult copy(String id) {
        String url = "http://"+host+"/icode/tplfile/tplset/"+id+"/copy";
        ResponseEntity<TplSetResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(null), new ParameterizedTypeReference<TplSetResult>() {});
        return response.getBody();
    }

    /**
	 * 根据ID查询公共代码模板集合
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public TplSetResult get(String id) {
        String url = "http://"+host+"/icode/tplfile/tplset/"+id;
        return restTemplate.getForObject(url, TplSetResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private TplSetResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询公共代码模板集合列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public TplSetPageResult list(PageSearchRequest<TplSetCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/tplfile/tplset/list";
        return restTemplate.postForObject(url, pageSearchRequest, TplSetPageResult.class);
    }
    public TplSetPageResult listFail(PageSearchRequest<TplSetCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        TplSetPageResult result = new TplSetPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private TplSetResult errorResult(){
        TplSetResult result = new TplSetResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
