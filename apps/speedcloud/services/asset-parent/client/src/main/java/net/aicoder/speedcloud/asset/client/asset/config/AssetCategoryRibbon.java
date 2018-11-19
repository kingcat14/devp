package net.aicoder.speedcloud.asset.client.asset.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryEditDto;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetCategoryPageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetCategoryResult;
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
 * 资产大类客户端
 * @author icode
 */
@Service
public class AssetCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryRibbon.class);

    private String host = "SPEEDCLOUD-ASSET-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增资产大类
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetCategoryResult add(AssetCategoryAddDto addDto) {
        String url = "http://"+host+"/asset/asset/config/assetcategory";
        return restTemplate.postForObject(url, addDto, AssetCategoryResult.class);
    }
    private AssetCategoryResult addFail(AssetCategoryAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资产大类
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetCategoryResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/config/assetcategory/"+id;
        ResponseEntity<AssetCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetCategoryResult>() {});
        return response.getBody();
    }
    private AssetCategoryResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资产大类
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetCategoryResult update(Long id, AssetCategoryEditDto editDto) {
        String url = "http://"+host+"/asset/asset/config/assetcategory/"+id;
        ResponseEntity<AssetCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetCategoryResult>() {});
        return response.getBody();
    }

    public AssetCategoryResult updateFail(Long id, AssetCategoryEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资产大类
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetCategoryResult get(Long id) {
        String url = "http://"+host+"/asset/asset/config/assetcategory/"+id;
        return restTemplate.getForObject(url, AssetCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetCategoryResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资产大类列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetCategoryPageResult list(PageSearchRequest<AssetCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/config/assetcategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetCategoryPageResult.class);
    }
    public AssetCategoryPageResult listFail(PageSearchRequest<AssetCategoryCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetCategoryPageResult result = new AssetCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetCategoryResult errorResult(){
        AssetCategoryResult result = new AssetCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
