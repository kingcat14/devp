package com.yunkang.saas.application.business.application.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.application.business.application.client.result.AppCategoryPageResult;
import com.yunkang.saas.application.business.application.client.result.AppCategoryResult;
import com.yunkang.saas.application.business.application.dto.AppCategoryAddDto;
import com.yunkang.saas.application.business.application.dto.AppCategoryCondition;
import com.yunkang.saas.application.business.application.dto.AppCategoryEditDto;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
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
 * 应用类别客户端
 * @author icode
 */
@Service
public class AppCategoryRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppCategoryRibbon.class);

    private String host = "APPLICATION";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增应用类别
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppCategoryResult add(AppCategoryAddDto addDto) {
        String url = "http://"+host+"/application/appCategory";
        return restTemplate.postForObject(url, addDto, AppCategoryResult.class);
    }
    private AppCategoryResult addFail(AppCategoryAddDto addDto) {
        AppCategoryResult result = new AppCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除应用类别
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppCategoryResult delete(Long id) {
        String url = "http://"+host+"/application/appCategory/"+id;
        ResponseEntity<AppCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppCategoryResult>() {});
        return response.getBody();
    }
    private AppCategoryResult deleteFail(Long id) {
        AppCategoryResult result = new AppCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新应用类别
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppCategoryResult update(Long id, AppCategoryEditDto editDto) {
        String url = "http://"+host+"/application/appCategory/"+id;
        ResponseEntity<AppCategoryResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppCategoryResult>() {});
        return response.getBody();
    }

    public AppCategoryResult updateFail(Long id, AppCategoryEditDto updateRequest) {
        AppCategoryResult result = new AppCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询应用类别
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppCategoryResult get(Long id) {
        String url = "http://"+host+"/application/appCategory/"+id;
        return restTemplate.getForObject(url, AppCategoryResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppCategoryResult getFail(Long id) {
        AppCategoryResult result = new AppCategoryResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询应用类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppCategoryPageResult list(PageSearchRequest<AppCategoryCondition> pageSearchRequest) {
        String url = "http://"+host+"/application/appCategory/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppCategoryPageResult.class);
    }
    public AppCategoryPageResult listFail(PageSearchRequest<AppCategoryCondition> pageSearchRequest) {
        AppCategoryPageResult result = new AppCategoryPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
