package net.aicoder.speedcloud.icode.business.domain.controller;

import io.swagger.annotations.Api;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.service.DomainService;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理领域
 * @author icode
 */
@Api(description = "领域", tags = "Domain")
@RestController
@RequestMapping(value = "/icode/domain/domain")
public class DomainActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainActionController.class);


	@Autowired
	private DomainService domainService;

	@Autowired
	private DomainController domainController;

	/**
	 * 复制领域对象
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}/copy")
	public DomainVO copy(@PathVariable String id){
		Domain domain = domainService.copy(id);
		return domainController.initViewProperty(domain);
	}




}

