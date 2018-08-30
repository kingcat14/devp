package com.kingzoo.kingcat.project.icode4;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.kingzoo.kingcat.project.icode4.business.tplfile.dao.TplCodeDao;
import com.kingzoo.kingcat.project.icode4.business.tplfile.dao.TplSetDao;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplSetCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Icode4Application.class)
public class Icode4ApplicationTests {

	@Autowired
	private TplCodeDao tplCodeDao;

	@Autowired
	private TplSetDao tplSetDao;


	/*
	 * update tpl_code t set t.file_name = (select file_name from icode.code_tpl c where c.id = t.id) ,t.content = (select c2.content from icode.code_tpl c2 where c2.id = t.id) where t.tpl_set_id = 33
	 */

//	@Test
	public void contextLoads() {

		TplCodeCondition codeCondition = new TplCodeCondition();
		codeCondition.setTplSetId("33");
		//codeCondition.setCode("JAVA_DOMAIN");


		List<TplCode> tplCodeList = tplCodeDao.findAll(codeCondition);
		//TplCode tplCode = tplCodeDao.findOne("340");

		for(TplCode tplCode: tplCodeList) {
			a(tplCode);
		}

	}

	//final String TPL_SET_CODE = "ICODE3_EXT6_SPRING_REST_JPA";
//	final String TPL_SET_CODE = "DEVP_APP_SPRING_REST_JPA";
	final String TPL_SET_CODE = "AVALON_SYS_SPRING_REST_JPA";
	/**
	 * 从数据库把代码模板复制到硬盘上
	 */
	@Test
	public void syncToDisk() throws IOException {
		TplSetCondition condition = new TplSetCondition();
		condition.setCode(TPL_SET_CODE);
		List<TplSet> list = tplSetDao.findAll(condition);
		for(TplSet tplSet : list){
			TplCodeCondition tplCodeCondition = new TplCodeCondition();
			tplCodeCondition.setTplSetId(tplSet.getId());
			List<TplCode> tplCodeList = tplCodeDao.findAll(tplCodeCondition);

			for(TplCode tplCode : tplCodeList){
				File file = new File("ftl"+File.separator + tplSet.getCode()+ File.separator+tplCode.getType()+File.separator + tplCode.getCode());
				Files.createParentDirs(file);

				if(!file.exists()){
					file.createNewFile();
				}

				CharSink charSink = Files.asCharSink(file, Charset.forName("UTF-8"));
				charSink.write(tplCode.getContent());
			}
		}
	}

	@Test
	public void syncToDB() throws IOException {
		String folderName = TPL_SET_CODE;

		TplSetCondition setCondition = new TplSetCondition();
		setCondition.setCode(folderName);
		TplSet tplSet = tplSetDao.findOne(tplSetDao.buildQuery(setCondition));
		if(tplSet == null){
			return;
		}

		File folder = new File("/Users/gonghongrui/studio/git_121/itools/icode4/ftl/"+folderName);

		File[] files =  folder.listFiles();

		for(File file : files){
			String tplCodeCode = file.getName();
			TplCodeCondition codeCondition = new TplCodeCondition();
			codeCondition.setTplSetId(tplSet.getId());
			codeCondition.setCode(tplCodeCode);
			TplCode tplCode = tplCodeDao.findOne(tplCodeDao.buildQuery(codeCondition));
			tplCode.setContent(Files.asCharSource(file, Charset.forName("UTF-8")).read());
			tplCodeDao.save(tplCode);
		}
		System.out.println("");
	}


	public void a(TplCode tplCode){
		String content = tplCode.getContent();
		content = content
				.replace("${project.basePackage}", "${system.basePackage}")
				.replace("${project.name", "${system.code")
				.replace("${domain.name", "${module.code")
				.replace("domain.displayName", "domain.name")
				.replace("model.domain", "module")
				.replace("model.name", "model.code")
				.replace("model.displayName", "model.name")
				.replace("pro.name","pro.code")
				.replace("pro.displayName", "pro.name");
		tplCode.setContent(content);

		String filePath = tplCode.getFilePath().replace("${project","${system")
				.replace("${system.name","${system.code")
				.replace("${model.domain.name}", "${module.code}");
		tplCode.setFilePath(filePath);

		String fileName = tplCode.getFileName()
				.replace("${model.name}", "${model.code}");
		tplCode.setFileName(fileName);

		tplCodeDao.save(tplCode);
	}

}
