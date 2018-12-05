package net.aicoder.speedcloud.icode.client.domain;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 领域对象行为参数客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entityactionparameter", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entityactionparameter", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface EntityActionParameterClient {


    /**
     * 新增领域对象行为参数
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<EntityActionParameterVO> add(@RequestBody EntityActionParameterAddDto addDto);


    /**
    * 删除领域对象行为参数
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<EntityActionParameterVO> delete(@PathVariable("id") String id);

    /**
    * 更新领域对象行为参数
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<EntityActionParameterVO> update(@PathVariable("id") String id, @RequestBody EntityActionParameterEditDto editDto);

    /**
    * 根据ID查询领域对象行为参数
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<EntityActionParameterVO> get(@PathVariable("id") String id);

    /**
     * 根据ID查询领域对象的详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}/detail")
    RestResponse<EntityActionParameterVO> getDetail(@PathVariable("id") String id);

    /**
    * 查询领域对象行为参数列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<EntityActionParameterVO>> list(@RequestBody PageSearchRequest<EntityActionParameterCondition> pageSearchRequest);

}
