package net.aicoder.speedcloud.business.pipeline.task.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionTypeProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionTypePropertyService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionTypeService;
import net.aicoder.speedcloud.business.pipeline.task.valid.PipelineTaskActionTypePropertyValidator;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypePropertyVO;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypeVO;
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
 * 管理操作类型属性定义
 * @author icode
 */
@Api(description = "操作类型属性定义", tags = "PipelineTaskActionTypeProperty")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetaskactiontypeproperty")
public class PipelineTaskActionTypePropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypePropertyController.class);


	@Autowired
	private PipelineTaskActionTypePropertyService pipelineTaskActionTypePropertyService;

	@Autowired
	private PipelineTaskActionTypeService pipelineTaskActionTypeService;

    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineTaskActionTypePropertyValidator pipelineTaskActionTypePropertyValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskActionTypePropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作类型属性定义
	 * @param pipelineTaskActionTypePropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作类型属性定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineTaskActionTypePropertyVO add(@RequestBody @Valid PipelineTaskActionTypePropertyAddDto pipelineTaskActionTypePropertyAddDto){
		PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty = new PipelineTaskActionTypeProperty();
		BeanUtils.copyProperties(pipelineTaskActionTypePropertyAddDto, pipelineTaskActionTypeProperty);

		pipelineTaskActionTypePropertyService.add(pipelineTaskActionTypeProperty);

		return  initViewProperty(pipelineTaskActionTypeProperty);
	}

	/**
	 * 删除操作类型属性定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作类型属性定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskActionTypeProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskActionTypePropertyService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新操作类型属性定义
	 * @param pipelineTaskActionTypePropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产操作类型属性定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineTaskActionTypePropertyVO update(@RequestBody @Valid PipelineTaskActionTypePropertyEditDto pipelineTaskActionTypePropertyEditDto, @PathVariable Long id){
		PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty = new PipelineTaskActionTypeProperty();
		BeanUtils.copyProperties(pipelineTaskActionTypePropertyEditDto, pipelineTaskActionTypeProperty);
		pipelineTaskActionTypeProperty.setId(id);
		pipelineTaskActionTypePropertyService.merge(pipelineTaskActionTypeProperty);

		PipelineTaskActionTypePropertyVO vo = initViewProperty(pipelineTaskActionTypeProperty);
		return  vo;
	}

	/**
	 * 根据ID查询操作类型属性定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作类型属性定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineTaskActionTypePropertyVO get(@PathVariable Long id) {

		PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty = pipelineTaskActionTypePropertyService.find(id);

		PipelineTaskActionTypePropertyVO vo = initViewProperty(pipelineTaskActionTypeProperty);
		return vo;
	}

	/**
	 * 查询操作类型属性定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作类型属性定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskActionTypePropertyVO> list(@RequestBody PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineTaskActionTypeProperty> page = pipelineTaskActionTypePropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskActionTypePropertyVO> voList = new ArrayList<>();
		for(PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty : page.getContent()){
			voList.add(initViewProperty(pipelineTaskActionTypeProperty));
		}

		PageContent<PipelineTaskActionTypePropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作类型属性定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作类型属性定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskActionTypePropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskActionTypePropertyVO> content = this.list(pageSearchRequest);

        List<PipelineTaskActionTypePropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskActionTypePropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("taskType" ,"所属任务类型");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("type" ,"属性类型");
            headMap.put("optionValue" ,"可选值");

        String title = new String("操作类型属性定义");
        String fileName = new String(("操作类型属性定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTaskActionTypePropertyVO initViewProperty(PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty){

	    PipelineTaskActionTypePropertyVO vo = new PipelineTaskActionTypePropertyVO();
        BeanUtils.copyProperties(pipelineTaskActionTypeProperty, vo);

		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASKACTIONTYPEPROPERTY-TYPE", pipelineTaskActionTypeProperty.getType());

		if(typeSimpleConfig!=null) {
		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
			vo.setTypeVO(typeSimpleConfigVO);
		}

	    //初始化其他对象
	    initTaskTypePropertyGroup(vo, pipelineTaskActionTypeProperty);
        return vo;

	}


	private void initTaskTypePropertyGroup(PipelineTaskActionTypePropertyVO pipelineTaskActionTypePropertyVO, PipelineTaskActionTypeProperty pipelineTaskActionTypeProperty){
	
		PipelineTaskActionType taskType = pipelineTaskActionTypeService.find(pipelineTaskActionTypeProperty.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineTaskActionTypeVO taskTypeVO = new PipelineTaskActionTypeVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineTaskActionTypePropertyVO.setTaskTypeVO(taskTypeVO);

	}


}

