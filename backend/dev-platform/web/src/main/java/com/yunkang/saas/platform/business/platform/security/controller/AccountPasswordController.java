package com.yunkang.saas.platform.business.platform.security.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.platform.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.business.platform.security.dto.AccountPasswordAddDto;
import com.yunkang.saas.platform.business.platform.security.dto.AccountPasswordCondition;
import com.yunkang.saas.platform.business.platform.security.dto.AccountPasswordEditDto;
import com.yunkang.saas.platform.business.platform.security.service.AccountPasswordService;
import com.yunkang.saas.platform.business.platform.security.valid.AccountPasswordValidator;
import com.yunkang.saas.platform.business.platform.security.vo.AccountPasswordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理账号密码
 * @author icode
 */
@RestController
@RequestMapping(value = "/platform/security/accountPassword")
public class AccountPasswordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountPasswordController.class);


	@Autowired
	private AccountPasswordService accountPasswordService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new AccountPasswordValidator());
	}

	/**
	 * 新增账号密码
	 * @param accountPasswordAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AccountPasswordVO add(@RequestBody @Valid AccountPasswordAddDto accountPasswordAddDto){
		AccountPassword accountPassword = new AccountPassword();
		BeanUtils.copyProperties(accountPasswordAddDto, accountPassword);

		accountPasswordService.add(accountPassword);

		return  initViewProperty(accountPassword);
	}

	/**
	 * 删除账号密码,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete accountPassword :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			accountPasswordService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新账号密码
	 * @param accountPasswordEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	AccountPasswordVO update(@RequestBody @Valid AccountPasswordEditDto accountPasswordEditDto, @PathVariable Long id){
		AccountPassword accountPassword = new AccountPassword();
		BeanUtils.copyProperties(accountPasswordEditDto, accountPassword);
		accountPassword.setId(id);
		accountPasswordService.merge(accountPassword);

		AccountPasswordVO vo = initViewProperty(accountPassword);
		return  vo;
	}

	/**
	 * 根据ID查询账号密码
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  AccountPasswordVO get(@PathVariable Long id) {

		AccountPassword accountPassword = accountPasswordService.find(id);

		AccountPasswordVO vo = initViewProperty(accountPassword);
		return vo;
	}

	/**
	 * 查询账号密码列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<AccountPasswordVO> list(@RequestBody PageSearchRequest<AccountPasswordCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<AccountPassword> page = accountPasswordService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AccountPasswordVO> voList = new ArrayList<>();
		for(AccountPassword accountPassword : page.getContent()){
			voList.add(initViewProperty(accountPassword));
		}

		PageContent<AccountPasswordVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private AccountPasswordVO initViewProperty(AccountPassword accountPassword){
	    AccountPasswordVO vo = new AccountPasswordVO();

        BeanUtils.copyProperties(accountPassword, vo);

	    //初始化其他对象
        return vo;
	}




}
