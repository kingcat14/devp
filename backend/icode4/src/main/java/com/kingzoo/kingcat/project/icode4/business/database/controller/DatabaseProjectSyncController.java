package com.kingzoo.kingcat.project.icode4.business.database.controller;

import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabseProjectSyncTask;
import com.kingzoo.kingcat.project.icode4.business.database.service.DatabseProjectSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 同步数据库的对象至本地
 */
@RestController
@RequestMapping(value = "/database/databaseProjectSync")
public class DatabaseProjectSyncController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseProjectSyncController.class);


	@Autowired
	private DatabseProjectSyncService databseProjectSyncService;


	/**
	 * 使用数据库生成代码的入口
	 * @param databseProjectSyncTask
	 * @return
	 */
	@PostMapping()
	public @ResponseBody
	void sync(@RequestBody DatabseProjectSyncTask databseProjectSyncTask){

		LOGGER.info("receive sync task for db:{}", databseProjectSyncTask);

		databseProjectSyncService.sync(databseProjectSyncTask);

	}

}
