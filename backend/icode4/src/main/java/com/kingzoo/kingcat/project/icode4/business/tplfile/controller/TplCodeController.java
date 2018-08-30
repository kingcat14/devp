package com.kingzoo.kingcat.project.icode4.business.tplfile.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.ModelType;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModelTypeService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo.TplCodeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplCodeService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplSetService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.valid.TplCodeValidator;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeVO;
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
 * 管理文件模板
 * @author icode
 */
@RestController
@RequestMapping(value = "/tplfile/tplCode")
public class TplCodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplCodeController.class);


	@Autowired
	private TplCodeService tplCodeService;

	@Autowired
	private ModelTypeService modelTypeService;
	@Autowired
	private TplSetService tplSetService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new TplCodeValidator());
	}

	/**
	 * 新增文件模板
	 * @param tplCodeAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TplCodeVO add(@RequestBody @Valid TplCodeAddRequest tplCodeAddRequest){
		TplCode tplCode = new TplCode();
		BeanUtils.copyProperties(tplCodeAddRequest, tplCode);

		tplCodeService.add(tplCode);

		return  initViewProperty(tplCode);
	}

	/**
	 * 删除文件模板,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tplCode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			tplCodeService.delete(id);
		}

	}

	/**
	 * 更新文件模板
	 * @param tplCode
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public TplCodeVO update(@RequestBody @Valid TplCode tplCode, @PathVariable String id){
		tplCode.setId(id);
		tplCodeService.merge(tplCode);

		TplCodeVO vo = initViewProperty(tplCode);
		return  vo;
	}

	/**
	 * 根据ID查询文件模板
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public TplCodeVO get(@PathVariable String id) {

		TplCode tplCode = tplCodeService.find(id);

		if(tplCode == null){
			return null;
		}
		TplCodeVO vo = initViewProperty(tplCode);
		return vo;
	}

	@PutMapping(value="/{id}/copy")
	public TplCodeVO copy(
			@PathVariable String id){

		TplCode tplCode = tplCodeService.copy(id);

		if(tplCode == null){
			return null;
		}
		TplCodeVO vo = initViewProperty(tplCode);
		return vo;
	}

	/**
	 * 查询文件模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<TplCodeVO> list(@RequestBody PageSearchRequest<TplCodeCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<TplCode> page = tplCodeService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<TplCodeVO> voList = new ArrayList<>();
		for(TplCode tplCode : page.getContent()){
			voList.add(initViewProperty(tplCode));
		}

		PageContent<TplCodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TplCodeVO initViewProperty(TplCode tplCode){
	    TplCodeVO vo = new TplCodeVO();

        BeanUtils.copyProperties(tplCode, vo);

	    //初始化其他对象
		initAcceptModelTypeProperty(vo, tplCode);

		initTplSetProperty(vo, tplCode);

        return vo;
	}



	private void initAcceptModelTypeProperty(TplCodeVO tplCodeVO, TplCode tplCode){
		if(StringUtils.isEmpty(tplCode.getAcceptModelTypeId())){
			return ;
		}
		//初始化其他对象
		ModelType acceptModelType = modelTypeService.find(tplCode.getAcceptModelTypeId());
        if(acceptModelType!=null) {
            tplCodeVO.setAcceptModelTypeName(acceptModelType.getName());
        }
	}
	
	private void initTplSetProperty(TplCodeVO tplCodeVO, TplCode tplCode){
		if(StringUtils.isEmpty(tplCode.getTplSetId())){
			return ;
		}
		//初始化其他对象
		TplSet tplSet = tplSetService.find(tplCode.getTplSetId());
        if(tplSet!=null) {
            tplCodeVO.setTplSetName(tplSet.getName());
        }
	}
	

}
