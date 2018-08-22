package net.aicoder.devp.client.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.client.product.result.DevpPrdProductPageResult;
import net.aicoder.devp.client.product.result.DevpPrdProductResult;

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
 * 产品定义客户端
 * @author icode
 */
@Service
public class DevpPrdProductRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductRibbon.class);

    private String host = "DEVP";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增产品定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpPrdProductResult add(DevpPrdProductAddDto addDto) {
        String url = "http://"+host+"/product/devpPrdProduct";
        return restTemplate.postForObject(url, addDto, DevpPrdProductResult.class);
    }
    private DevpPrdProductResult addFail(DevpPrdProductAddDto addDto) {
        DevpPrdProductResult result = new DevpPrdProductResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpPrdProductResult delete(Long id) {
        String url = "http://"+host+"/product/devpPrdProduct/"+id;
        ResponseEntity<DevpPrdProductResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpPrdProductResult>() {});
        return response.getBody();
    }
    private DevpPrdProductResult deleteFail(Long id) {
        DevpPrdProductResult result = new DevpPrdProductResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpPrdProductResult update(Long id, DevpPrdProductEditDto editDto) {
        String url = "http://"+host+"/product/devpPrdProduct/"+id;
        ResponseEntity<DevpPrdProductResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpPrdProductResult>() {});
        return response.getBody();
    }

    public DevpPrdProductResult updateFail(Long id, DevpPrdProductEditDto updateRequest) {
        DevpPrdProductResult result = new DevpPrdProductResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpPrdProductResult get(Long id) {
        String url = "http://"+host+"/product/devpPrdProduct/"+id;
        return restTemplate.getForObject(url, DevpPrdProductResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpPrdProductResult getFail(Long id) {
        DevpPrdProductResult result = new DevpPrdProductResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpPrdProductPageResult list(PageSearchRequest<DevpPrdProductCondition> pageSearchRequest) {
        String url = "http://"+host+"/product/devpPrdProduct/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpPrdProductPageResult.class);
    }
    public DevpPrdProductPageResult listFail(PageSearchRequest<DevpPrdProductCondition> pageSearchRequest) {
        DevpPrdProductPageResult result = new DevpPrdProductPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
