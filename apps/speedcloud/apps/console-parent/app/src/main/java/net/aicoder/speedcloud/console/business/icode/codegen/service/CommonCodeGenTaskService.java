package net.aicoder.speedcloud.console.business.icode.codegen.service;

import com.google.common.io.Files;
import com.yunkang.saas.common.util.IDGenerator;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.CodeGenModel;
import net.aicoder.speedcloud.console.business.icode.codegen.util.ImprovedNamingStrategyOfMethod;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * 生成代码
 */
@Service("commonCodeGenTaskService")
@Slf4j
public class CommonCodeGenTaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeGenTaskService.class);

    private Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    private Configuration pathCfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
	StringTemplateLoader pathTemplateLoader = new StringTemplateLoader();

    @PostConstruct
	public void init(){
		cfg.setTemplateLoader(stringTemplateLoader);
		pathCfg.setTemplateLoader(pathTemplateLoader);
	}

	@Transactional
	public void add(CodeGenModel codeGenModel) throws Exception {

		//TODO 这样做并发的时候有问题
		//cfg.setTemplateLoader(stringTemplateLoader);

		TplCodeVO tplCode = codeGenModel.getTplCode();
		stringTemplateLoader.putTemplate(tplCode.getId()+"", tplCode.getContent());

		if(StringUtils.isEmpty(tplCode.getAcceptModelType())){
			genCode(tplCode, codeGenModel);
		}
		if(!StringUtils.contains(tplCode.getAcceptModelType(), codeGenModel.getModelType())){
			throw new Exception("跳过,模板("+tplCode.getAcceptModelType()+")不支持该种类型对象("+codeGenModel.getModelType()+")");
		}

		genCode(tplCode, codeGenModel);
	}

    /**
     * 生成代码(单个模板和对象)
     * @param tpl
     * @param model
     */
    public void genCode(TplCodeVO tpl, CodeGenModel model) throws Exception {

		stringTemplateLoader.putTemplate(tpl.getId()+"", tpl.getContent());

		model.setImprovedNamingStrategy(new ImprovedNamingStrategyOfMethod());

	    String fileName = this.getAbsoluteFileName(model);

	    File file = new File(fileName);
	    if(file.exists()){

		    if(!tpl.getOverridable()){
			    LOGGER.info("TPL({}, id:{}) can not override. ignor it.", tpl.getCode(), tpl.getId());
			    throw new Exception("跳过(文件已存在, 且模板设置为不能覆盖)");
		    }else {
			    file.delete();
		    }
	    }
	    try {

		    Files.createParentDirs(file);
		    file.createNewFile();
			LOGGER.info("create file:{}", file.getAbsolutePath());
	    } catch (IOException e) {
		    LOGGER.error("", e);
	    }

	    try(FileOutputStream fileOut = new FileOutputStream(file)) {

            Writer out = new OutputStreamWriter(fileOut, "UTF-8");

 	        Template temp = cfg.getTemplate(tpl.getId()+"");
	        temp.process(model, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            LOGGER.error("生成文件出错:"+tpl.getCode(), e);
        }
    }

    protected String getAbsoluteFileName(CodeGenModel commonCodeGenModel) throws Exception {

	    String filePath = File.separator + commonCodeGenModel.getTplCode().getFilePath();
		filePath = formatPathAndName(filePath, commonCodeGenModel);
	    filePath = filePath.replace(".",File.separator);
	    filePath = commonCodeGenModel.getPath() + filePath;

	    String fileName = filePath + File.separator+ commonCodeGenModel.getTplCode().getFileName();
		fileName = formatPathAndName(fileName, commonCodeGenModel);

	    return fileName;
    }

	private String formatPathAndName(String target, CodeGenModel codeGenModel) throws IOException, TemplateException {

		String filePath = target;

		StringWriter out = new StringWriter();

		String id = IDGenerator.uuid();
		pathTemplateLoader.putTemplate(id, filePath);

		pathCfg.getTemplate(id).process(codeGenModel, out);

		pathCfg.removeTemplateFromCache(id);

		return out.toString();


	}

}
