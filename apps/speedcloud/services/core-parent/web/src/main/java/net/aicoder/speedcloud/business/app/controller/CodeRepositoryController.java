package net.aicoder.speedcloud.business.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.service.AppBaseInfoService;
import net.aicoder.speedcloud.business.app.service.CodeRepositoryService;
import net.aicoder.speedcloud.business.app.valid.CodeRepositoryValidator;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import net.aicoder.speedcloud.business.config.domain.CodeRepositoryType;
import net.aicoder.speedcloud.business.config.domain.DevelopType;
import net.aicoder.speedcloud.business.config.service.CodeRepositoryTypeService;
import net.aicoder.speedcloud.business.config.service.DevelopTypeService;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
import net.aicoder.speedcloud.business.config.vo.DevelopTypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理代码库
 * @author icode
 */
@Api(description = "代码库", tags = "CodeRepository")
@RestController
@RequestMapping(value = "/speedcloud/app/coderepository")
public class CodeRepositoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryController.class);


	@Autowired
	private CodeRepositoryService codeRepositoryService;

	@Autowired
	private CodeRepositoryTypeService codeRepositoryTypeService;
	@Autowired
	private DevelopTypeService developTypeService;
	@Autowired
	private AppBaseInfoService appBaseInfoService;


	@Autowired
	private CodeRepositoryValidator codeRepositoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeRepositoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库
	 * @param codeRepositoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.app.coderepository.add", count = true)
	public CodeRepositoryVO add(@RequestBody @Valid CodeRepositoryAddDto codeRepositoryAddDto){
		CodeRepository codeRepository = new CodeRepository();
		BeanUtils.copyProperties(codeRepositoryAddDto, codeRepository);

		codeRepositoryService.add(codeRepository);

		return  initViewProperty(codeRepository);
	}

	/**
	 * 删除代码库,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.app.coderepository.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeRepository :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			codeRepositoryService.delete(id);
		}

	}

	/**
	 * 更新代码库
	 * @param codeRepositoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改代码库(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.coderepository.update", count = true)
	public	CodeRepositoryVO update(@RequestBody @Valid CodeRepositoryEditDto codeRepositoryEditDto, @PathVariable String id){
		CodeRepository codeRepository = codeRepositoryService.find(id);
		BeanUtils.copyProperties(codeRepositoryEditDto, codeRepository);
		codeRepository.setId(id);
		codeRepositoryService.merge(codeRepository);

		CodeRepositoryVO vo = initViewProperty(codeRepository);
		return  vo;
	}

	/**
	 * 根据ID查询代码库
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询代码库", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.coderepository.get")
	public  CodeRepositoryVO get(@PathVariable String id) {

		CodeRepository codeRepository = codeRepositoryService.find(id);
		if(codeRepository == null){
			throw new ResourceNotFoundException("找不到指定的代码库，请检查ID");
		}
		CodeRepositoryVO vo = initViewProperty(codeRepository);
		return vo;
	}

	/**
	 * 查询代码库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.app.coderepository.list")
	public PageContent<CodeRepositoryVO> list(@RequestBody PageSearchRequest<CodeRepositoryCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<CodeRepository> page = codeRepositoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<CodeRepositoryVO> voList = new ArrayList<>();
		for(CodeRepository codeRepository : page.getContent()){
			voList.add(initViewProperty(codeRepository));
		}

		PageContent<CodeRepositoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出代码库列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出代码库列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(CodeRepositoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<CodeRepositoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeRepositoryVO> content = this.list(pageSearchRequest);

        List<CodeRepositoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeRepositoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("name" ,"名称");
        headMap.put("type" ,"代码库类型");
        headMap.put("url" ,"url");
        headMap.put("developType" ,"开发模式");
        headMap.put("username" ,"用户名");
        headMap.put("description" ,"描述");
        headMap.put("app" ,"应用");

        String title = new String("代码库");
        String fileName = new String(("代码库_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private CodeRepositoryVO initViewProperty(CodeRepository codeRepository){

	    CodeRepositoryVO vo = new CodeRepositoryVO();
        BeanUtils.copyProperties(codeRepository, vo);


	    //初始化其他对象
	    initTypePropertyGroup(vo, codeRepository);
	    initDevelopTypePropertyGroup(vo, codeRepository);
	    initAppPropertyGroup(vo, codeRepository);
        return vo;

	}

	private void initTypePropertyGroup(CodeRepositoryVO codeRepositoryVO, CodeRepository codeRepository){
	
		CodeRepositoryType type = codeRepositoryTypeService.find(codeRepository.getType());
		if(type == null){
			return;
		}
		CodeRepositoryTypeVO typeVO = new CodeRepositoryTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		codeRepositoryVO.setTypeVO(typeVO);

	}
	private void initDevelopTypePropertyGroup(CodeRepositoryVO codeRepositoryVO, CodeRepository codeRepository){
	
		DevelopType developType = developTypeService.find(codeRepository.getDevelopType());
		if(developType == null){
			return;
		}
		DevelopTypeVO developTypeVO = new DevelopTypeVO();
		BeanUtils.copyProperties(developType, developTypeVO);

		codeRepositoryVO.setDevelopTypeVO(developTypeVO);

	}
	private void initAppPropertyGroup(CodeRepositoryVO codeRepositoryVO, CodeRepository codeRepository){
	
		AppBaseInfo app = appBaseInfoService.find(codeRepository.getApp());
		if(app == null){
			return;
		}
		AppBaseInfoVO appVO = new AppBaseInfoVO();
		BeanUtils.copyProperties(app, appVO);

		codeRepositoryVO.setAppVO(appVO);

	}

}

