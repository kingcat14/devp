package net.aicoder.speedcloud.icode.client.domain;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 领域客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/domain", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/domain", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface DomainFeignClient {

    /**
     * 新增领域
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<DomainVO> add(@RequestBody DomainAddDto addDto);

	/**
	 * 删除领域
	 * @param id
	 */
    @DeleteMapping("/{id}")
    RestResponse<DomainVO> delete(@PathVariable("id") String id);

	/**
	 * 更新领域
	 * @param id
	 * @param editDto
	 * @return
	 */
    @PutMapping("/{id}")
    RestResponse<DomainVO> update(@PathVariable("id") String id, @RequestBody DomainEditDto editDto);

    /**
     * 复制
     * @param id
     * @return
     */
    @PutMapping("/{id}/copy")
    RestResponse<DomainVO> copy(@PathVariable("id") String id);

    /**
	 * 根据ID查询领域
	 * @param id
	 * @return
	 */
    @GetMapping("/{id}")
    RestResponse<DomainVO> get(@PathVariable("id") String id);

    @GetMapping("/{id}/findCodePath")
	RestResponse<String> getCodePath(@PathVariable("id") String id);
	/**
	 * 查询领域列表
	 * @param pageSearchRequest
	 * @return
	 */
    @PostMapping("/list")
	RestResponse<PageContent<DomainVO>> list(@RequestBody PageSearchRequest<DomainCondition> pageSearchRequest);
    
}
