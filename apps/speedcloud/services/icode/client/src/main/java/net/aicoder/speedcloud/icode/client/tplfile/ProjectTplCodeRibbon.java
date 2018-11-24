package net.aicoder.speedcloud.icode.client.tplfile;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeEditDto;
import net.aicoder.speedcloud.icode.client.tplfile.result.ProjectTplCodePageResult;
import net.aicoder.speedcloud.icode.client.tplfile.result.ProjectTplCodeResult;
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
 * 组件代码模板客户端
 * @author icode
 */
@Service
public class ProjectTplCodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTplCodeRibbon.class);

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
     * 新增组件代码模板
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ProjectTplCodeResult add(ProjectTplCodeAddDto addDto) {
        String url = "http://"+host+"/icode/tplfile/projecttplcode";
        return restTemplate.postForObject(url, addDto, ProjectTplCodeResult.class);
    }
    private ProjectTplCodeResult addFail(ProjectTplCodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除组件代码模板
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ProjectTplCodeResult delete(String id) {
        String url = "http://"+host+"/icode/tplfile/projecttplcode/"+id;
        ResponseEntity<ProjectTplCodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ProjectTplCodeResult>() {});
        return response.getBody();
    }
    private ProjectTplCodeResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新组件代码模板
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ProjectTplCodeResult update(String id, ProjectTplCodeEditDto editDto) {
        String url = "http://"+host+"/icode/tplfile/projecttplcode/"+id;
        ResponseEntity<ProjectTplCodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ProjectTplCodeResult>() {});
        return response.getBody();
    }

    public ProjectTplCodeResult updateFail(String id, ProjectTplCodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询组件代码模板
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ProjectTplCodeResult get(String id) {
        String url = "http://"+host+"/icode/tplfile/projecttplcode/"+id;
        return restTemplate.getForObject(url, ProjectTplCodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ProjectTplCodeResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询组件代码模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ProjectTplCodePageResult list(PageSearchRequest<ProjectTplCodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/tplfile/projecttplcode/list";
        return restTemplate.postForObject(url, pageSearchRequest, ProjectTplCodePageResult.class);
    }
    public ProjectTplCodePageResult listFail(PageSearchRequest<ProjectTplCodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ProjectTplCodePageResult result = new ProjectTplCodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ProjectTplCodeResult errorResult(){
        ProjectTplCodeResult result = new ProjectTplCodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
