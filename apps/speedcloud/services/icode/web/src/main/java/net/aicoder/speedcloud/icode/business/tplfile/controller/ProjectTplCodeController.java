package net.aicoder.speedcloud.icode.business.tplfile.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.service.ComponentService;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.tplfile.domain.ProjectTplCode;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeEditDto;
import net.aicoder.speedcloud.icode.business.tplfile.service.ProjectTplCodeService;
import net.aicoder.speedcloud.icode.business.tplfile.service.TplCodeService;
import net.aicoder.speedcloud.icode.business.tplfile.valid.ProjectTplCodeValidator;
import net.aicoder.speedcloud.icode.business.tplfile.vo.ProjectTplCodeVO;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
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
 * 管理组件代码模板
 * @author icode
 */
@Api(description = "组件代码模板", tags = "ProjectTplCode")
@RestController
@RequestMapping(value = "/icode/tplfile/projecttplcode")
public class ProjectTplCodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTplCodeController.class);


	@Autowired
	private ProjectTplCodeService projectTplCodeService;

	@Autowired
	private ComponentService componentService;
	@Autowired
	private TplCodeService tplCodeService;


	@Autowired
	private ProjectTplCodeValidator projectTplCodeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(projectTplCodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件代码模板
	 * @param projectTplCodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件代码模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProjectTplCodeVO add(@RequestBody @Valid ProjectTplCodeAddDto projectTplCodeAddDto){
		ProjectTplCode projectTplCode = new ProjectTplCode();
		BeanUtils.copyProperties(projectTplCodeAddDto, projectTplCode);

		projectTplCodeService.add(projectTplCode);

		return  initViewProperty(projectTplCode);
	}

	/**
	 * 删除组件代码模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件代码模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete projectTplCode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			projectTplCodeService.delete(id);
		}

	}

	/**
	 * 更新组件代码模板
	 * @param projectTplCodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件代码模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	ProjectTplCodeVO update(@RequestBody @Valid ProjectTplCodeEditDto projectTplCodeEditDto, @PathVariable String id){
		ProjectTplCode projectTplCode = new ProjectTplCode();
		BeanUtils.copyProperties(projectTplCodeEditDto, projectTplCode);
		projectTplCode.setId(id);
		projectTplCodeService.merge(projectTplCode);

		ProjectTplCodeVO vo = initViewProperty(projectTplCode);
		return  vo;
	}

	/**
	 * 根据ID查询组件代码模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件代码模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  ProjectTplCodeVO get(@PathVariable String id) {

		ProjectTplCode projectTplCode = projectTplCodeService.find(id);

		ProjectTplCodeVO vo = initViewProperty(projectTplCode);
		return vo;
	}

	/**
	 * 查询组件代码模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件代码模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
	public PageContent<ProjectTplCodeVO> list(@RequestBody PageSearchRequest<ProjectTplCodeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ProjectTplCode> page = projectTplCodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ProjectTplCodeVO> voList = new ArrayList<>();
		for(ProjectTplCode projectTplCode : page.getContent()){
			voList.add(initViewProperty(projectTplCode));
		}

		PageContent<ProjectTplCodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件代码模板列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件代码模板列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ProjectTplCodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ProjectTplCodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ProjectTplCodeVO> content = this.list(pageSearchRequest);

        List<ProjectTplCodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ProjectTplCodeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("component" ,"所属系统组件");
            headMap.put("tplCode" ,"关联的代码模板");
            headMap.put("autoUpdate" ,"自动刷新");

        String title = new String("组件代码模板");
        String fileName = new String(("组件代码模板_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ProjectTplCodeVO initViewProperty(ProjectTplCode projectTplCode){

	    ProjectTplCodeVO vo = new ProjectTplCodeVO();
        BeanUtils.copyProperties(projectTplCode, vo);


	    //初始化其他对象
	    initComponentPropertyGroup(vo, projectTplCode);
	    initTplCodePropertyGroup(vo, projectTplCode);
        return vo;

	}


	private void initComponentPropertyGroup(ProjectTplCodeVO projectTplCodeVO, ProjectTplCode projectTplCode){
	
		Component component = componentService.find(projectTplCode.getComponent());
		if(component == null){
			return;
		}
		ComponentVO componentVO = new ComponentVO();
		BeanUtils.copyProperties(component, componentVO);

		projectTplCodeVO.setComponentVO(componentVO);

	}


	private void initTplCodePropertyGroup(ProjectTplCodeVO projectTplCodeVO, ProjectTplCode projectTplCode){
	
		TplCode tplCode = tplCodeService.find(projectTplCode.getTplCode());
		if(tplCode == null){
			return;
		}
		TplCodeVO tplCodeVO = new TplCodeVO();
		BeanUtils.copyProperties(tplCode, tplCodeVO);

		projectTplCodeVO.setTplCodeVO(tplCodeVO);

	}


}

