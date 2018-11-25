package net.aicoder.speedcloud.console.business.icode.tplfile.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetEditDto;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import net.aicoder.speedcloud.icode.client.tplfile.TplSetRibbon;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplSetPageResult;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplSetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tplSetRibbonService")
public class TplSetRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplSetRibbonService.class);


	@Autowired
	private TplSetRibbon tplSetRibbon;


	public TplSetVO add(TplSetAddDto addDto){
		TplSetResult result = tplSetRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		TplSetResult result = tplSetRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}
	}
	public TplSetVO merge(String id, TplSetEditDto editDto){
		TplSetResult result = tplSetRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public TplSetVO copy(String id){
		TplSetResult result = tplSetRibbon.copy(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public TplSetVO find(String id){
		TplSetResult result = tplSetRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<TplSetVO> list(PageSearchRequest<TplSetCondition> pageSearchRequest) {
		TplSetPageResult result = tplSetRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
