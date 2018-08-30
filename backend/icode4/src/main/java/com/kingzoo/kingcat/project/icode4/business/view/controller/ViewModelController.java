package com.kingzoo.kingcat.project.icode4.business.view.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.view.controller.vo.ViewModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelService;
import com.kingzoo.kingcat.project.icode4.business.view.valid.ViewModelValidator;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelCondition;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理展现对象
 * @author icode
 */
@RestController
@RequestMapping(value = "/view/viewModel")
public class ViewModelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewModelController.class);


	@Autowired
	private ViewModelService viewModelService;

	@Autowired
	private DomainModelService domainModelService;
	@Autowired
	private ModuleService moduleService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ViewModelValidator());
	}

	/**
	 * 新增展现对象
	 * @param viewModelAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ViewModelVO add(@RequestBody @Valid ViewModelAddRequest viewModelAddRequest){
		ViewModel viewModel = new ViewModel();
		BeanUtils.copyProperties(viewModelAddRequest, viewModel);

		viewModelService.add(viewModel);

		return  initViewProperty(viewModel);
	}

	/**
	 * 删除展现对象,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray) {

		LOGGER.debug("delete viewModel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids) {
			viewModelService.delete(id);
		}
	}

	@PutMapping(value="/createFromDomainModel/{id}")
	public ResponseEntity<String> createFromDomainModel(@PathVariable String id){

		viewModelService.createFromDomainModel(id);

		ResponseEntity<String> responseResult = new ResponseEntity<>(
				"{success:true,message:'data  copy finish'}", HttpStatus.OK);

		return responseResult;
	}

	/**
	 * 更新展现对象
	 * @param viewModel
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public ViewModelVO update(@RequestBody @Valid ViewModel viewModel, @PathVariable String id){
		viewModel.setId(id);
		viewModelService.merge(viewModel);

		ViewModelVO vo = initViewProperty(viewModel);
		return  vo;
	}

	/**
	 * 根据ID查询展现对象
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ViewModelVO get(@PathVariable String id) {

		ViewModel viewModel = viewModelService.find(id);

		ViewModelVO vo = initViewProperty(viewModel);
		return vo;
	}

	/**
	 * 查询展现对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ViewModelVO> list(@RequestBody PageSearchRequest<ViewModelCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<ViewModel> page = viewModelService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ViewModelVO> voList = new ArrayList<>();
		for(ViewModel viewModel : page.getContent()){
			voList.add(initViewProperty(viewModel));
		}

		PageContent<ViewModelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ViewModelVO initViewProperty(ViewModel viewModel){
	    ViewModelVO vo = new ViewModelVO();

        BeanUtils.copyProperties(viewModel, vo);

	    //初始化其他对象
		initDomainModelProperty(vo, viewModel);

		initModuleProperty(vo, viewModel);

        return vo;
	}



	private void initDomainModelProperty(ViewModelVO viewModelVO, ViewModel viewModel){
		if(StringUtils.isEmpty(viewModel.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(viewModel.getDomainModelId());
        if(domainModel!=null) {
            viewModelVO.setDomainModelName(domainModel.getName());
        }
	}
	
	private void initModuleProperty(ViewModelVO viewModelVO, ViewModel viewModel){
		if(StringUtils.isEmpty(viewModel.getModuleId())){
			return ;
		}
		//初始化其他对象
		Module module = moduleService.find(viewModel.getModuleId());
        if(module!=null) {
            viewModelVO.setModuleName(module.getName());
        }
	}
	

}
