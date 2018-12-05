package net.aicoder.speedcloud.icode.client.domain;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityViewPropertyVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 领域对象展现属性客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entityviewproperty", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entityviewproperty", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface EntityViewPropertyClient {

    /**
     * 根据EntityProperty创建一个EntityViewProperty
     * @param id
     * @return
     */
    @PostMapping("/createByProperty/{id}")
    RestResponse<EntityViewPropertyVO> createByProperty(@PathVariable("id") String id);

    /**
     * 新增领域对象展现属性
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<EntityViewPropertyVO> add(@RequestBody EntityViewPropertyAddDto addDto);


    /**
    * 删除领域对象展现属性
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<EntityViewPropertyVO> delete(@PathVariable("id") String id);

    /**
    * 更新领域对象展现属性
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<EntityViewPropertyVO> update(@PathVariable("id") String id, @RequestBody EntityViewPropertyEditDto editDto);

    /**
    * 根据ID查询领域对象展现属性
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<EntityViewPropertyVO> get(@PathVariable("id") String id);

    /**
    * 查询领域对象展现属性列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<EntityViewPropertyVO>> list(@RequestBody PageSearchRequest<EntityViewPropertyCondition> pageSearchRequest);

}
