package com.kingzoo.kingcat.project.icode4.util;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.hibernate.cfg.NamingStrategy;

import java.util.List;

/**
 * 奖NameName修改成name_name
 */
public class ImprovedNamingStrategyOfMethod implements TemplateMethodModelEx {

	NamingStrategy strategy = ImprovedNamingStrategy.INSTANCE;

	@Override
	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() > 2) {
			throw new TemplateModelException("Wrong arguments");
		}

		String content = args.get(0).toString();
		String result = strategy.classToTableName(content);
		if (args.size() > 1) {
			content = args.get(1).toString();
			result = strategy.classToTableName(content) + result;
		}
		return new SimpleScalar(result);

	}

}