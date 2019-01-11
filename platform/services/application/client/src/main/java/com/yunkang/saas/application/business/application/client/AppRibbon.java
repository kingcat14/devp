package com.yunkang.saas.application.business.application.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.application.business.application.client.result.AppPageResult;
import com.yunkang.saas.application.business.application.client.result.AppResult;
import com.yunkang.saas.application.business.application.dto.AppAddDto;
import com.yunkang.saas.application.business.application.dto.AppCondition;
import com.yunkang.saas.application.business.application.dto.AppEditDto;
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
 * 应用客户端
 * @author icode
 */
@Service
public class AppRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppRibbon.class);

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
     * 新增应用
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppResult add(AppAddDto addDto) {
        String url = "http://"+host+"/application/app";
        return restTemplate.postForObject(url, addDto, AppResult.class);
    }
    private AppResult addFail(AppAddDto addDto) {
        AppResult result = new AppResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除应用
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppResult delete(Long id) {
        String url = "http://"+host+"/application/app/"+id;
        ResponseEntity<AppResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppResult>() {});
        return response.getBody();
    }
    private AppResult deleteFail(Long id) {
        AppResult result = new AppResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新应用
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppResult update(Long id, AppEditDto editDto) {
        String url = "http://"+host+"/application/app/"+id;
        ResponseEntity<AppResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppResult>() {});
        return response.getBody();
    }

    public AppResult updateFail(Long id, AppEditDto updateRequest) {
        AppResult result = new AppResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询应用
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppResult get(Long id) {
        String url = "http://"+host+"/application/app/"+id;
        return restTemplate.getForObject(url, AppResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppResult getFail(Long id) {
        AppResult result = new AppResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppPageResult list(PageSearchRequest<AppCondition> pageSearchRequest) {
        String url = "http://"+host+"/application/app/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppPageResult.class);
    }
    public AppPageResult listFail(PageSearchRequest<AppCondition> pageSearchRequest) {
        AppPageResult result = new AppPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
