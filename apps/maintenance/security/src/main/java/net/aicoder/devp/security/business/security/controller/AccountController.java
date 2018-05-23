package net.aicoder.devp.security.business.security.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import net.aicoder.devp.security.business.security.domain.Account;
import net.aicoder.devp.security.business.security.dto.AccountAddDto;
import net.aicoder.devp.security.business.security.dto.AccountCondition;
import net.aicoder.devp.security.business.security.dto.AccountEditDto;
import net.aicoder.devp.security.business.security.service.AccountService;
import net.aicoder.devp.security.business.security.valid.AccountValidator;
import net.aicoder.devp.security.business.security.vo.AccountVO;
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
 * 管理账号
 * @author icode
 */
@RestController
@RequestMapping(value = "/security/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);


	@Autowired
	private AccountService accountService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new AccountValidator());
	}

	/**
	 * 新增账号
	 * @param accountAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AccountVO add(@RequestBody @Valid AccountAddDto accountAddDto){
		Account account = new Account();
		BeanUtils.copyProperties(accountAddDto, account);

		accountService.add(account);

		return  initViewProperty(account);
	}

	/**
	 * 删除账号,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete account :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			accountService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新账号
	 * @param accountEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	AccountVO update(@RequestBody @Valid AccountEditDto accountEditDto, @PathVariable Long id){
		Account account = new Account();
		BeanUtils.copyProperties(accountEditDto, account);
		account.setId(id);
		accountService.merge(account);

		AccountVO vo = initViewProperty(account);
		return  vo;
	}

	/**
	 * 根据ID查询账号
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  AccountVO get(@PathVariable Long id) {

		Account account = accountService.find(id);

		AccountVO vo = initViewProperty(account);
		return vo;
	}

	/**
	 * 查询账号列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<AccountVO> list(@RequestBody PageSearchRequest<AccountCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<Account> page = accountService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AccountVO> voList = new ArrayList<>();
		for(Account account : page.getContent()){
			voList.add(initViewProperty(account));
		}

		PageContent<AccountVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private AccountVO initViewProperty(Account account){
	    AccountVO vo = new AccountVO();

        BeanUtils.copyProperties(account, vo);

	    //初始化其他对象
        return vo;
	}




}
