package com.kingzoo.kingcat.project.icode4.business.codegen.controller;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.CodeGenModel;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.CodeGenRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.service.CommonCodeGenTaskConvertService;
import com.kingzoo.kingcat.project.icode4.business.codegen.service.CommonCodeGenTaskService;
import com.kingzoo.kingcat.project.icode4.business.codegen.vo.CodeGenTaskVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author icode
 */
@RestController
@RequestMapping(value = "/codegen/domainmodel")
public class CodeGenDomainModelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenDomainModelController.class);



	@Autowired
	private CommonCodeGenTaskConvertService commonCodeGenTaskConvertService;

	@Autowired
	private CommonCodeGenTaskService commonCodeGenTaskService;

	/**
	 * 新增代码生成任务
	 * @param codeGenRequest
	 * @return
	 */
	@PostMapping("/task")
	@ResponseStatus( HttpStatus.CREATED )
	public CodeGenTaskVO task(@RequestBody CodeGenRequest codeGenRequest){

		CodeGenModel codeGenModel = commonCodeGenTaskConvertService.convert(codeGenRequest);
		CodeGenTaskVO vo = new CodeGenTaskVO();
		BeanUtils.copyProperties(codeGenRequest, vo);
		vo.setId(IDGenerator.get());
		try{
			commonCodeGenTaskService.add(codeGenModel);
			vo.setStatus("执行成功");
		}catch(Exception e){
			vo.setStatus("执行异常");
		}

		return  vo;
	}



	private CodeGenTaskVO initViewProperty(CodeGenRequest codeGenRequest){
	    CodeGenTaskVO vo = new CodeGenTaskVO();

        BeanUtils.copyProperties(codeGenRequest, vo);

	    //初始化其他对象
        return vo;
	}




}
