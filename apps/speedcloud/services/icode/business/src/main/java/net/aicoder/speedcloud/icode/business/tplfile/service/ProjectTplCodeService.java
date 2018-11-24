package net.aicoder.speedcloud.icode.business.tplfile.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.tplfile.dao.ProjectTplCodeDao;
import net.aicoder.speedcloud.icode.business.tplfile.dao.ProjectTplCodeSpecification;
import net.aicoder.speedcloud.icode.business.tplfile.domain.ProjectTplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("projectTplCodeService")
@Slf4j
public class ProjectTplCodeService  extends GenericCrudService<ProjectTplCode, String, ProjectTplCodeCondition, ProjectTplCodeDao> {

	@Override
	public Specification<ProjectTplCode> getSpecification(ProjectTplCodeCondition condition) {
		return new ProjectTplCodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ProjectTplCode.PROPERTY_CODE);
		return sort;
	}
}