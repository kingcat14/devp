package net.aicoder.speedcloud.business.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理流水线
 * @author icode
 */
@Api(description = "流水线", tags = "Pipeline")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipeline")
public class PipelineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineController.class);


	@Autowired
	private PipelineService pipelineService;

	@Autowired
	private ProjectService projectService;

    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineValidator pipelineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线
	 * @param pipelineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineVO add(@RequestBody @Valid PipelineAddDto pipelineAddDto){

		Pipeline pipeline = pipelineService.create(pipelineAddDto);

		return  initViewProperty(pipeline);
	}

	/**
	 * 删除流水线,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipeline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线
	 * @param pipelineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineVO update(@RequestBody @Valid PipelineEditDto pipelineEditDto, @PathVariable Long id){

		pipelineEditDto.setId(id);
		Pipeline pipeline = pipelineService.update(pipelineEditDto);

		PipelineVO vo = initViewProperty(pipeline);
		return  vo;
	}

	/**
	 * 根据ID查询流水线
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineVO get(@PathVariable Long id) {

		Pipeline pipeline = pipelineService.find(id);

		PipelineVO vo = initViewProperty(pipeline);
		return vo;
	}

	/**
	 * 查询流水线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineVO> list(@RequestBody PageSearchRequest<PipelineCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<Pipeline> page = pipelineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineVO> voList = new ArrayList<>();
		for(Pipeline pipeline : page.getContent()){
			voList.add(initViewProperty(pipeline));
		}

		PageContent<PipelineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineVO> content = this.list(pageSearchRequest);

        List<PipelineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"流水线名称");
            headMap.put("type" ,"类型");
            headMap.put("project" ,"所属产品");

        String title = new String("流水线");
        String fileName = new String(("流水线_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineVO initViewProperty(Pipeline pipeline){

	    PipelineVO vo = new PipelineVO();
        BeanUtils.copyProperties(pipeline, vo);

		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINE-TYPE", pipeline.getType());

		if(typeSimpleConfig!=null) {
		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
			vo.setTypeVO(typeSimpleConfigVO);
		}

	    //初始化其他对象
	    initProjectPropertyGroup(vo, pipeline);
        return vo;

	}


	private void initProjectPropertyGroup(PipelineVO pipelineVO, Pipeline pipeline){
	
		Project project = projectService.find(pipeline.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		pipelineVO.setProjectVO(projectVO);

	}


}

