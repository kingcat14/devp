package net.aicoder.speedcloud.console.business.icode.codegen.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.util.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.CodeGenModel;
import net.aicoder.speedcloud.console.business.icode.codegen.dto.CodeGenTaskAddDto;
import net.aicoder.speedcloud.console.business.icode.codegen.dto.CodeGenTaskVO;
import net.aicoder.speedcloud.console.business.icode.codegen.service.CommonCodeGenTaskConvertService;
import net.aicoder.speedcloud.console.business.icode.codegen.service.CommonCodeGenTaskService;
import net.aicoder.speedcloud.console.business.icode.codegen.valid.CodeGenTaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 *
 * @author icode
 */
@RestController
@RequestMapping(value = "/codegen/domainmodel")
@Slf4j
public class CodeGenDomainModelController {

	@Autowired
	private CommonCodeGenTaskConvertService commonCodeGenTaskConvertService;

	@Autowired
	private CommonCodeGenTaskService commonCodeGenTaskService;

	@Autowired
	private CodeGenTaskValidator codeGenTaskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeGenTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码生成任务
	 * @param codeGenTaskVO
	 * @return
	 */
	@PostMapping("/task")
	@ResponseStatus( HttpStatus.CREATED )
	public CodeGenTaskVO task(@RequestBody @Valid CodeGenTaskAddDto codeGenTaskVO) throws Exception {

		CodeGenModel codeGenModel = commonCodeGenTaskConvertService.convert(codeGenTaskVO);
		CodeGenTaskVO vo = new CodeGenTaskVO();
		try {
			commonCodeGenTaskService.add(codeGenModel);
			vo.setId(IDGenerator.uuid());
			vo.setStatus("执行成功");
		}catch (Exception e){
			vo.setStatus(e.getMessage());
		}

		return  vo;
	}







}
