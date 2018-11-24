package net.aicoder.speedcloud.icode.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ProductAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductEditDto;
import net.aicoder.speedcloud.icode.client.project.result.ProductPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ProductResult;
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
 * 产品客户端
 * @author icode
 */
@Service
public class ProductRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRibbon.class);

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
     * 新增产品
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ProductResult add(ProductAddDto addDto) {
        String url = "http://"+host+"/icode/project/product";
        return restTemplate.postForObject(url, addDto, ProductResult.class);
    }
    private ProductResult addFail(ProductAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除产品
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ProductResult delete(String id) {
        String url = "http://"+host+"/icode/project/product/"+id;
        ResponseEntity<ProductResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ProductResult>() {});
        return response.getBody();
    }
    private ProductResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新产品
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ProductResult update(String id, ProductEditDto editDto) {
        String url = "http://"+host+"/icode/project/product/"+id;
        ResponseEntity<ProductResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ProductResult>() {});
        return response.getBody();
    }

    public ProductResult updateFail(String id, ProductEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询产品
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ProductResult get(String id) {
        String url = "http://"+host+"/icode/project/product/"+id;
        return restTemplate.getForObject(url, ProductResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ProductResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询产品列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ProductPageResult list(PageSearchRequest<ProductCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/project/product/list";
        return restTemplate.postForObject(url, pageSearchRequest, ProductPageResult.class);
    }
    public ProductPageResult listFail(PageSearchRequest<ProductCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ProductPageResult result = new ProductPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ProductResult errorResult(){
        ProductResult result = new ProductResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
