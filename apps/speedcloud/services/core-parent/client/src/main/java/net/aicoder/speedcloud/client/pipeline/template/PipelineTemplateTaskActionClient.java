package net.aicoder.speedcloud.client.pipeline.template;


import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskActionVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 操作模板客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetaskaction", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetaskaction", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface PipelineTemplateTaskActionClient {


    /**
     * 新增操作模板
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<PipelineTemplateTaskActionVO> add(@RequestBody PipelineTemplateTaskActionAddDto addDto);


    /**
    * 删除操作模板
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<PipelineTemplateTaskActionVO> delete(@PathVariable("id") String id);

    /**
    * 更新操作模板
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<PipelineTemplateTaskActionVO> update(@PathVariable("id") String id, @RequestBody PipelineTemplateTaskActionEditDto editDto);

    /**
    * 根据ID查询操作模板
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<PipelineTemplateTaskActionVO> get(@PathVariable("id") String id);

    /**
    * 查询操作模板列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<PipelineTemplateTaskActionVO>> list(@RequestBody PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest);

}
