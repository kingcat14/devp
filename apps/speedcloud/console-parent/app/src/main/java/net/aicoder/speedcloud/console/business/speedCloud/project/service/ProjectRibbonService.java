package net.aicoder.speedcloud.console.business.speedCloud.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.project.ProjectRibbon;
import net.aicoder.speedcloud.client.project.result.ProjectPageResult;
import net.aicoder.speedcloud.client.project.result.ProjectResult;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("projectRibbonService")
public class ProjectRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRibbonService.class);


	@Autowired
	private ProjectRibbon projectRibbon;


	public ProjectVO add(ProjectAddDto addDto){
		ProjectResult result = projectRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		ProjectResult result = projectRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ProjectVO merge(Long id, ProjectEditDto editDto){
		ProjectResult result = projectRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ProjectVO find(Long id){
		ProjectResult result = projectRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ProjectVO> list(PageSearchRequest<ProjectCondition> pageSearchRequest) {
		ProjectPageResult result = projectRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
