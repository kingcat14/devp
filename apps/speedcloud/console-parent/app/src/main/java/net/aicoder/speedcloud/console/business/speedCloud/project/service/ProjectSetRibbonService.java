package net.aicoder.speedcloud.console.business.speedCloud.project.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.project.ProjectSetRibbon;
import net.aicoder.speedcloud.client.project.result.ProjectSetPageResult;
import net.aicoder.speedcloud.client.project.result.ProjectSetResult;
import net.aicoder.speedcloud.business.project.dto.ProjectSetCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectSetAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectSetEditDto;
import net.aicoder.speedcloud.business.project.vo.ProjectSetVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("projectSetRibbonService")
public class ProjectSetRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSetRibbonService.class);


	@Autowired
	private ProjectSetRibbon projectSetRibbon;


	public ProjectSetVO add(ProjectSetAddDto addDto){
		ProjectSetResult result = projectSetRibbon.add(addDto);

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
		ProjectSetResult result = projectSetRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}
	}
	public ProjectSetVO merge(Long id, ProjectSetEditDto editDto){
		ProjectSetResult result = projectSetRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ProjectSetVO find(Long id){
		ProjectSetResult result = projectSetRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ProjectSetVO> list(PageSearchRequest<ProjectSetCondition> pageSearchRequest) {
		ProjectSetPageResult result = projectSetRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PROJECT", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
