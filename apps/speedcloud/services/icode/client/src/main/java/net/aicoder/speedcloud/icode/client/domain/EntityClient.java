package net.aicoder.speedcloud.icode.client.domain;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 领域对象客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entity", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/entity", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface EntityClient {


    /**
     * 新增领域对象
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<EntityVO> add(@RequestBody EntityAddDto addDto);


    /**
    * 删除领域对象
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<EntityVO> delete(@PathVariable("id") String id);

    /**
    * 更新领域对象
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<EntityVO> update(@PathVariable("id") String id, @RequestBody EntityEditDto editDto);

    @PutMapping("/{id}/copy")
    RestResponse<EntityVO> copy(@PathVariable("id") String id);

    /**
    * 根据ID查询领域对象
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<EntityVO> get(@PathVariable("id") String id);

    /**
     * 根据ID查询领域对象的详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}/detail")
    RestResponse<EntityVO> getDetail(@PathVariable("id") String id);

    /**
    * 查询领域对象列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<EntityVO>> list(@RequestBody PageSearchRequest<EntityCondition> pageSearchRequest);

}
