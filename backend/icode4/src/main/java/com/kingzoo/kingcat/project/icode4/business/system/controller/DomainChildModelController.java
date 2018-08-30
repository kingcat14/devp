package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainChildModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainChildModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainChildModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.DomainChildModelValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainChildModelCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainChildModelVO;
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
 * 管理领域子对象
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/domainChildModel")
public class DomainChildModelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainChildModelController.class);


	@Autowired
	private DomainChildModelService domainChildModelService;

	@Autowired
	private DomainModelService domainModelService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DomainChildModelValidator());
	}

	/**
	 * 新增领域子对象
	 * @param domainChildModelAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DomainChildModelVO add(@RequestBody @Valid DomainChildModelAddRequest domainChildModelAddRequest){
		DomainChildModel domainChildModel = new DomainChildModel();
		BeanUtils.copyProperties(domainChildModelAddRequest, domainChildModel);

		domainChildModelService.add(domainChildModel);

		return  initViewProperty(domainChildModel);
	}

	/**
	 * 删除领域子对象,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete domainChildModel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			domainChildModelService.delete(id);
		}

	}

	/**
	 * 更新领域子对象
	 * @param domainChildModel
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DomainChildModelVO update(@RequestBody @Valid DomainChildModel domainChildModel, @PathVariable String id){
		domainChildModel.setId(id);
		domainChildModelService.merge(domainChildModel);

		DomainChildModelVO vo = initViewProperty(domainChildModel);
		return  vo;
	}

	/**
	 * 根据ID查询领域子对象
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DomainChildModelVO get(@PathVariable String id) {

		DomainChildModel domainChildModel = domainChildModelService.find(id);

		DomainChildModelVO vo = initViewProperty(domainChildModel);
		return vo;
	}

	/**
	 * 查询领域子对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DomainChildModelVO> list(@RequestBody PageSearchRequest<DomainChildModelCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<DomainChildModel> page = domainChildModelService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<DomainChildModelVO> voList = new ArrayList<>();
		for(DomainChildModel domainChildModel : page.getContent()){
			voList.add(initViewProperty(domainChildModel));
		}

		PageContent<DomainChildModelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DomainChildModelVO initViewProperty(DomainChildModel domainChildModel){
	    DomainChildModelVO vo = new DomainChildModelVO();

        BeanUtils.copyProperties(domainChildModel, vo);

	    //初始化其他对象
		initDomainModelProperty(vo, domainChildModel);

        return vo;
	}



	private void initDomainModelProperty(DomainChildModelVO domainChildModelVO, DomainChildModel domainChildModel){
		if(StringUtils.isEmpty(domainChildModel.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(domainChildModel.getDomainModelId());
        if(domainModel!=null) {
            domainChildModelVO.setDomainModelName(domainModel.getName());
        }
	}
	

}
