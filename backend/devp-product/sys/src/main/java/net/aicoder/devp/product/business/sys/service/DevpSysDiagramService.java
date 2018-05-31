package net.aicoder.devp.product.business.sys.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.product.business.sys.dao.DevpSysDiagramDao;
import net.aicoder.devp.product.business.sys.dao.DevpSysDiagramSpecification;
import net.aicoder.devp.product.business.sys.domain.DevpSysDiagram;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDiagramService")
public class DevpSysDiagramService  extends CrudService<DevpSysDiagram, DevpSysDiagramCondition, DevpSysDiagramDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDiagramService.class);

	@Override
	public Specification<DevpSysDiagram> getSpecification(DevpSysDiagramCondition condition) {
		return new DevpSysDiagramSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDiagram.PROPERTY_TID);
		return sort;
	}
}