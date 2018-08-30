package com.kingzoo.kingcat.project.icode4.business.tplfile.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo.TplSetAddRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplSetService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.valid.TplSetValidator;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplSetCondition;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplSetVO;
import org.apache.commons.lang3.StringUtils;
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
 * 管理模板集合
 * @author icode
 */
@RestController
@RequestMapping(value = "/tplfile/tplSet")
public class TplSetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplSetController.class);


	@Autowired
	private TplSetService tplSetService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new TplSetValidator());
	}

	/**
	 * 新增模板集合
	 * @param tplSetAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TplSetVO add(@RequestBody @Valid TplSetAddRequest tplSetAddRequest){
		TplSet tplSet = new TplSet();
		BeanUtils.copyProperties(tplSetAddRequest, tplSet);

		tplSetService.add(tplSet);

		return  initViewProperty(tplSet);
	}

	/**
	 * 删除模板集合,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tplSet :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			tplSetService.delete(id);
		}

	}

	/**
	 * 更新模板集合
	 * @param tplSet
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public TplSetVO update(@RequestBody @Valid TplSet tplSet, @PathVariable String id){
		tplSet.setId(id);
		tplSetService.merge(tplSet);

		TplSetVO vo = initViewProperty(tplSet);
		return  vo;
	}

	/**
	 * 根据ID查询模板集合
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public TplSetVO get(@PathVariable String id) {

		TplSet tplSet = tplSetService.find(id);

		TplSetVO vo = initViewProperty(tplSet);
		return vo;
	}


	@PutMapping(value="/{id}/copy")
	@ResponseBody
	public TplSet copy(
			@PathVariable String id){

		TplSet tplSet = tplSetService.copy(id);
		return  tplSet;
	}

	/**
	 * 查询模板集合列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<TplSetVO> list(@RequestBody PageSearchRequest<TplSetCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<TplSet> page = tplSetService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<TplSetVO> voList = new ArrayList<>();
		for(TplSet tplSet : page.getContent()){
			voList.add(initViewProperty(tplSet));
		}

		PageContent<TplSetVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TplSetVO initViewProperty(TplSet tplSet){
	    TplSetVO vo = new TplSetVO();

        BeanUtils.copyProperties(tplSet, vo);

	    //初始化其他对象
		initParentProperty(vo, tplSet);

        return vo;
	}



	private void initParentProperty(TplSetVO tplSetVO, TplSet tplSet){
		if(StringUtils.isEmpty(tplSet.getParentId())){
			return ;
		}
		//初始化其他对象
		TplSet parent = tplSetService.find(tplSet.getParentId());
        if(parent!=null) {
            tplSetVO.setParentName(parent.getName());
        }
	}
	

}
