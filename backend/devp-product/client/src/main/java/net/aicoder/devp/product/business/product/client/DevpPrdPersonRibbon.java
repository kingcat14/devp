package net.aicoder.devp.product.business.product.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPersonPageResult;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPersonResult;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonEditDto;
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
 * 产品干系人客户端
 * @author icode
 */
@Service
public class DevpPrdPersonRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPersonRibbon.class);

    String host = "PRODUCT";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * 新增产品干系人
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpPrdPersonResult add(DevpPrdPersonAddDto addDto) {
        String url = "http://"+host+"/product/devpPrdPerson";
        return restTemplate.postForObject(url, addDto, DevpPrdPersonResult.class);
    }
    private DevpPrdPersonResult addFail(DevpPrdPersonAddDto addDto) {
        DevpPrdPersonResult result = new DevpPrdPersonResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品干系人
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpPrdPersonResult delete(Long id) {
        String url = "http://"+host+"/product/devpPrdPerson/"+id;
        ResponseEntity<DevpPrdPersonResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpPrdPersonResult>() {});
        return response.getBody();
    }
    private DevpPrdPersonResult deleteFail(Long id) {
        DevpPrdPersonResult result = new DevpPrdPersonResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品干系人
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpPrdPersonResult update(Long id, DevpPrdPersonEditDto editDto) {
        String url = "http://"+host+"/product/devpPrdPerson/"+id;
        ResponseEntity<DevpPrdPersonResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpPrdPersonResult>() {});
        return response.getBody();
    }

    public DevpPrdPersonResult updateFail(Long id, DevpPrdPersonEditDto updateRequest) {
        DevpPrdPersonResult result = new DevpPrdPersonResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品干系人
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpPrdPersonResult get(Long id) {
        String url = "http://"+host+"/product/devpPrdPerson/"+id;
        return restTemplate.getForObject(url, DevpPrdPersonResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpPrdPersonResult getFail(Long id) {
        DevpPrdPersonResult result = new DevpPrdPersonResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品干系人列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpPrdPersonPageResult list(PageSearchRequest<DevpPrdPersonCondition> pageSearchRequest) {
        String url = "http://"+host+"/product/devpPrdPerson/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpPrdPersonPageResult.class);
    }
    public DevpPrdPersonPageResult listFail(PageSearchRequest<DevpPrdPersonCondition> pageSearchRequest) {
        DevpPrdPersonPageResult result = new DevpPrdPersonPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
