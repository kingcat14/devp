package com.demo.client.domain;

import com.demo.client.domain.dto.DomainAddDto;
import com.demo.client.domain.dto.DomainCondition;
import com.demo.client.domain.dto.DomainEditDto;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 领域客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-ICODE-MICROSERVICE", path = "/icode/domain/domain", decode404 = true)
public interface DomainFeignClient {

	
    /**
     * 新增领域
     * @param addDto
     * @return
     */
    @PostMapping
    DomainResult add(@RequestBody DomainAddDto addDto);


	/**
	 * 删除领域
	 * @param id
	 */
    @DeleteMapping("/{id}")
    DomainResult delete(@PathVariable("id") String id);

	/**
	 * 更新领域
	 * @param id
	 * @param editDto
	 * @return
	 */
    @PutMapping("/{id}")
    DomainResult update(@PathVariable("id") String id, @RequestBody DomainEditDto editDto);

    /**
     * 复制
     * @param id
     * @return
     */
    @PutMapping("/{id}/copy")
    DomainResult copy(@PathVariable("id") String id);



    /**
	 * 根据ID查询领域
	 * @param id
	 * @return
	 */
    @GetMapping("/{id}")
    DomainResult get(@PathVariable("id") String id);

	/**
	 * 查询领域列表
	 * @param pageSearchRequest
	 * @return
	 */
    @PostMapping("/list")
    DomainPageResult list(@RequestBody PageSearchRequest<DomainCondition> pageSearchRequest);
    
}
