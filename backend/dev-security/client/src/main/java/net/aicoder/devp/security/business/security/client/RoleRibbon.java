package net.aicoder.devp.security.business.security.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.security.business.security.client.result.RolePageResult;
import net.aicoder.devp.security.business.security.client.result.RoleResult;
import net.aicoder.devp.security.business.security.dto.RoleAddDto;
import net.aicoder.devp.security.business.security.dto.RoleCondition;
import net.aicoder.devp.security.business.security.dto.RoleEditDto;
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
 * 角色客户端
 * @author icode
 */
@Service
public class RoleRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleRibbon.class);

    private String host = "SECURITY";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增角色
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public RoleResult add(RoleAddDto addDto) {
        String url = "http://"+host+"/security/role";
        return restTemplate.postForObject(url, addDto, RoleResult.class);
    }
    private RoleResult addFail(RoleAddDto addDto) {
        RoleResult result = new RoleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除角色
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public RoleResult delete(Long id) {
        String url = "http://"+host+"/security/role/"+id;
        ResponseEntity<RoleResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<RoleResult>() {});
        return response.getBody();
    }
    private RoleResult deleteFail(Long id) {
        RoleResult result = new RoleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新角色
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public RoleResult update(Long id, RoleEditDto editDto) {
        String url = "http://"+host+"/security/role/"+id;
        ResponseEntity<RoleResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<RoleResult>() {});
        return response.getBody();
    }

    public RoleResult updateFail(Long id, RoleEditDto updateRequest) {
        RoleResult result = new RoleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询角色
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public RoleResult get(Long id) {
        String url = "http://"+host+"/security/role/"+id;
        return restTemplate.getForObject(url, RoleResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private RoleResult getFail(Long id) {
        RoleResult result = new RoleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询角色列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public RolePageResult list(PageSearchRequest<RoleCondition> pageSearchRequest) {
        String url = "http://"+host+"/security/role/list";
        return restTemplate.postForObject(url, pageSearchRequest, RolePageResult.class);
    }
    public RolePageResult listFail(PageSearchRequest<RoleCondition> pageSearchRequest) {
        RolePageResult result = new RolePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
