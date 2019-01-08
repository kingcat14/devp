package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.controller;

import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service.PipelineTemplateTaskRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid.PipelineTemplateTaskValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 管理任务模板
 * @author icode
 */
@Api(description = "任务模板", tags = "PipelineTemplateTask")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/template/pipelinetemplatetask")
public class PipelineTemplateTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskController.class);

	@Autowired
	private SaaSUtil saaSUtil;

    @Autowired
	private PipelineTemplateTaskRibbonService pipelineTemplateTaskRibbonService;

	@Autowired
	private PipelineTemplateTaskValidator pipelineTemplateTaskValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTemplateTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务模板
	 * @param pipelineTemplateTaskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public PipelineTemplateTaskVO add(@RequestBody @Valid PipelineTemplateTaskAddDto pipelineTemplateTaskAddDto){
		if(CollectionUtils.isNotEmpty(pipelineTemplateTaskAddDto.getActions())){
			List<PipelineTemplateTaskActionAddDto> actionAddDtoList = pipelineTemplateTaskAddDto.getActions();
			for(PipelineTemplateTaskActionAddDto actionAddDto : actionAddDtoList){
				actionAddDto.setTid(saaSUtil.getAccount().getId());
			}
		}
		return  pipelineTemplateTaskRibbonService.add(pipelineTemplateTaskAddDto);
	}

	/**
	 * 删除任务模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTemplateTask :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			pipelineTemplateTaskRibbonService.delete(id);
		}

	}

	/**
	 * 更新任务模板
	 * @param pipelineTemplateTaskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public PipelineTemplateTaskVO update(@RequestBody @Valid PipelineTemplateTaskEditDto pipelineTemplateTaskEditDto, @ApiParam(value = "要查询的任务模板id") @PathVariable String id){

		PipelineTemplateTaskVO vo = pipelineTemplateTaskRibbonService.merge(id, pipelineTemplateTaskEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public PipelineTemplateTaskVO get(@ApiParam(value = "要查询的任务模板id") @PathVariable String id) {

		PipelineTemplateTaskVO vo = pipelineTemplateTaskRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = PipelineTemplateTaskCondition.class)
	public PageContent<PipelineTemplateTaskVO> list(@RequestBody @Valid PageSearchRequest<PipelineTemplateTaskCondition> pageSearchRequest){

		PageContent<PipelineTemplateTaskVO> pageContent = pipelineTemplateTaskRibbonService.list(pageSearchRequest);
		for(PipelineTemplateTaskVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}



	private PipelineTemplateTaskVO initViewProperty( PipelineTemplateTaskVO vo){

		SimpleConfig execTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETEMPLATETASK-EXECTYPE", vo.getExecType());

		if(execTypeSimpleConfig!=null) {

		    SimpleConfigVO execTypeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(execTypeSimpleConfig, execTypeSimpleConfigVO);
		    vo.setExecTypeVO(execTypeSimpleConfigVO);
		}


	   
        return vo;

	}
}
