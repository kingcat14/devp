package net.aicoder.speedcloud.client.pipeline.jenkins;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JenkinsAdapterVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * JenkinsAdapter客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/jenkins/jenkinsadapter", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/jenkins/jenkinsadapter", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface JenkinsAdapterClient {


    /**
     * 新增JenkinsAdapter
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<JenkinsAdapterVO> add(@RequestBody JenkinsAdapterAddDto addDto);


    /**
    * 删除JenkinsAdapter
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<JenkinsAdapterVO> delete(@PathVariable("id") String id);

    /**
    * 更新JenkinsAdapter
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<JenkinsAdapterVO> update(@PathVariable("id") String id, @RequestBody JenkinsAdapterEditDto editDto);

    /**
    * 根据ID查询JenkinsAdapter
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<JenkinsAdapterVO> get(@PathVariable("id") String id);

    /**
    * 查询JenkinsAdapter列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<JenkinsAdapterVO>> list(@RequestBody PageSearchRequest<JenkinsAdapterCondition> pageSearchRequest);

}
