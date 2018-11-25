package net.aicoder.speedcloud.console.business.icode.tplfile.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeEditDto;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import net.aicoder.speedcloud.icode.client.tplfile.TplCodeRibbon;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplCodePageResult;
import net.aicoder.speedcloud.icode.client.tplfile.result.TplCodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tplCodeRibbonService")
public class TplCodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplCodeRibbonService.class);


	@Autowired
	private TplCodeRibbon tplCodeRibbon;


	public TplCodeVO add(TplCodeAddDto addDto){
		TplCodeResult result = tplCodeRibbon.add(addDto);

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
		TplCodeResult result = tplCodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}
	}
	public TplCodeVO merge(String id, TplCodeEditDto editDto){
		TplCodeResult result = tplCodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public TplCodeVO find(String id){
		TplCodeResult result = tplCodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public TplCodeVO copy(String id){
		TplCodeResult result = tplCodeRibbon.copy(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}


	public PageContent<TplCodeVO> list(PageSearchRequest<TplCodeCondition> pageSearchRequest) {
		TplCodePageResult result = tplCodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "TPLFILE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
