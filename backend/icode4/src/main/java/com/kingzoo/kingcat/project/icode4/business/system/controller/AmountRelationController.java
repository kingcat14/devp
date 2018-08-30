package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.AmountRelationAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.AmountRelation;
import com.kingzoo.kingcat.project.icode4.business.system.service.AmountRelationService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.AmountRelationValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.AmountRelationCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.AmountRelationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理数量关系
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/amountRelation")
public class AmountRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmountRelationController.class);


	@Autowired
	private AmountRelationService amountRelationService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new AmountRelationValidator());
	}

	/**
	 * 新增数量关系
	 * @param amountRelationAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AmountRelationVO add(@RequestBody @Valid AmountRelationAddRequest amountRelationAddRequest){
		AmountRelation amountRelation = new AmountRelation();
		BeanUtils.copyProperties(amountRelationAddRequest, amountRelation);

		amountRelationService.add(amountRelation);

		return  initViewProperty(amountRelation);
	}

	/**
	 * 删除数量关系,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete amountRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			amountRelationService.delete(id);
		}

	}

	/**
	 * 更新数量关系
	 * @param amountRelation
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public AmountRelation update(@RequestBody @Valid AmountRelation amountRelation, @PathVariable String id){
		amountRelation.setId(id);
		amountRelationService.merge(amountRelation);

		initViewProperty(amountRelation);
		return  amountRelation;
	}

	/**
	 * 根据ID查询数量关系
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public AmountRelation get(@PathVariable String id) {

		AmountRelation amountRelation = amountRelationService.find(id);

		initViewProperty(amountRelation);
		return amountRelation;
	}

	/**
	 * 查询数量关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<AmountRelationVO> list(@RequestBody PageSearchRequest<AmountRelationCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<AmountRelation> page = amountRelationService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<AmountRelationVO> voList = new ArrayList<>();
		for(AmountRelation amountRelation : page.getContent()){
			voList.add(initViewProperty(amountRelation));
		}

		PageContent<AmountRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private AmountRelationVO initViewProperty(AmountRelation amountRelation){
	    AmountRelationVO vo = new AmountRelationVO();

        BeanUtils.copyProperties(amountRelation, vo);

	    //初始化其他对象
        return vo;
	}




}
