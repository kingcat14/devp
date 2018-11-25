package net.aicoder.speedcloud.icode.client.tplfile;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeEditDto;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplCodePageResult;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplCodeResult;
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
 * 公共代码模板客户端
 * @author icode
 */
@Service
public class TplCodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplCodeRibbon.class);

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
     * 新增公共代码模板
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public TplCodeResult add(TplCodeAddDto addDto) {
        String url = "http://"+host+"/icode/tplfile/tplcode";
        return restTemplate.postForObject(url, addDto, TplCodeResult.class);
    }
    private TplCodeResult addFail(TplCodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除公共代码模板
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public TplCodeResult delete(String id) {
        String url = "http://"+host+"/icode/tplfile/tplcode/"+id;
        ResponseEntity<TplCodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<TplCodeResult>() {});
        return response.getBody();
    }
    private TplCodeResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新公共代码模板
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public TplCodeResult update(String id, TplCodeEditDto editDto) {
        String url = "http://"+host+"/icode/tplfile/tplcode/"+id;
        ResponseEntity<TplCodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<TplCodeResult>() {});
        return response.getBody();
    }

    public TplCodeResult updateFail(String id, TplCodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

    /**
     * 根据ID复制公共代码模板
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFail")
    public TplCodeResult copy(String id) {
        String url = "http://"+host+"/icode/tplfile/tplcode/"+id+"/copy";
        ResponseEntity<TplCodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(null), new ParameterizedTypeReference<TplCodeResult>() {});
        return response.getBody();
    }

    /**
	 * 根据ID查询公共代码模板
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public TplCodeResult get(String id) {
        String url = "http://"+host+"/icode/tplfile/tplcode/"+id;
        return restTemplate.getForObject(url, TplCodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private TplCodeResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询公共代码模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public TplCodePageResult list(PageSearchRequest<TplCodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/tplfile/tplcode/list";
        return restTemplate.postForObject(url, pageSearchRequest, TplCodePageResult.class);
    }
    public TplCodePageResult listFail(PageSearchRequest<TplCodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        TplCodePageResult result = new TplCodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private TplCodeResult errorResult(){
        TplCodeResult result = new TplCodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
