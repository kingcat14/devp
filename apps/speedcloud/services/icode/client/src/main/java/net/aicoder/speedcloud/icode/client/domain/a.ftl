package ${system.basePackage}.client.${module.code?lower_case};

import com.yunkang.saas.bootstrap.config.client.feign.OAuth2FeignAutoConfiguration;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import ${system.basePackage}.business.${module.code?lower_case}.dto.${model.code?cap_first}AddDto;
import ${system.basePackage}.business.${module.code?lower_case}.dto.${model.code?cap_first}Condition;
import ${system.basePackage}.business.${module.code?lower_case}.dto.${model.code?cap_first}EditDto;
import ${system.basePackage}.business.${module.code?lower_case}.vo.${model.code?cap_first}VO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * ${model.name}客户端
 * @author icode
 */
@FeignClient(name = "${project.groupCode?upper_case}-${project.code?upper_case}-MICROSERVICE", path = "/${system.code?lower_case}/${module.code?lower_case?replace(".","/")}/${model.code?lower_case}", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface ${model.code?cap_first}FeignClient {


    /**
     * 新增${model.name}
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<${model.code?cap_first}VO> add(@RequestBody ${model.code?cap_first}AddDto addDto);


    /**
    * 删除${model.name}
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<DomainVO> delete(@PathVariable("id") String id);

    /**
    * 更新${model.name}
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<${model.code?cap_first}VO> update(@PathVariable("id") String id, @RequestBody ${model.code?cap_first}EditDto editDto);

    /**
    * 复制
    * @param id
    * @return
    */
    @PutMapping("/{id}/copy")
    RestResponse<${model.code?cap_first}VO> copy(@PathVariable("id") String id);



    /**
    * 根据ID查询${model.name}
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<${model.code?cap_first}VO> get(@PathVariable("id") String id);

    /**
    * 查询${model.name}列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<${model.code?cap_first}VO>> list(@RequestBody PageSearchRequest<${model.code?cap_first}Condition> pageSearchRequest);

}
