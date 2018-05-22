package net.aicoder.devp.product.business.product.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.client.DevpPrdPersonRibbon;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPersonPageResult;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPersonResult;
import net.aicoder.devp.product.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonEditDto;
import net.aicoder.devp.product.business.product.vo.DevpPrdPersonVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpPrdPersonRibbonService")
public class DevpPrdPersonRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPerson.class);


	@Autowired
	private DevpPrdPersonRibbon devpPrdPersonRibbon;


	public DevpPrdPersonVO add(DevpPrdPersonAddDto addDto){
		DevpPrdPersonResult result = devpPrdPersonRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpPrdPersonResult result = devpPrdPersonRibbon.delete(id);
	}
	public DevpPrdPersonVO merge(Long id, DevpPrdPersonEditDto editDto){
		DevpPrdPersonResult result = devpPrdPersonRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpPrdPersonVO find(Long id){
		DevpPrdPersonResult result = devpPrdPersonRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpPrdPersonVO> list(PageSearchRequest<DevpPrdPersonCondition> pageSearchRequest) {
		DevpPrdPersonPageResult result = devpPrdPersonRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
