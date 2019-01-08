package net.aicoder.speedcloud.client.pipeline.template;


import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskParamVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务模板参数客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetaskparam", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetaskparam", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface PipelineTemplateTaskParamClient {


    /**
     * 新增任务模板参数
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<PipelineTemplateTaskParamVO> add(@RequestBody PipelineTemplateTaskParamAddDto addDto);


    /**
    * 删除任务模板参数
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<PipelineTemplateTaskParamVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务模板参数
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<PipelineTemplateTaskParamVO> update(@PathVariable("id") String id, @RequestBody PipelineTemplateTaskParamEditDto editDto);

    /**
    * 根据ID查询任务模板参数
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<PipelineTemplateTaskParamVO> get(@PathVariable("id") String id);

    /**
    * 查询任务模板参数列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<PipelineTemplateTaskParamVO>> list(@RequestBody PageSearchRequest<PipelineTemplateTaskParamCondition> pageSearchRequest);

}
