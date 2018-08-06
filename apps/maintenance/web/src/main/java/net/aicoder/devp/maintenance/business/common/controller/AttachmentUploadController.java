package net.aicoder.devp.maintenance.business.common.controller;


import net.aicoder.devp.maintenance.business.common.domain.Attachment;
import net.aicoder.devp.maintenance.business.common.dto.FileIputResponse;
import net.aicoder.devp.maintenance.business.common.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 管理附件
 */
@Controller
@RequestMapping(value = "/common/attachment/upload")
public class AttachmentUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentUploadController.class);

	private String uploadPath;

	@Autowired
	private AttachmentService attachementService;

	@Value("${UPLOAD.FILE.PATH:/Users/gonghongrui/studio/git_aicoder/devp/apps/maintenance/web}")
	public void setUploadPath(String uploadFilePath) {
		uploadPath = uploadFilePath;
	}





	@PostMapping(value = "/upload")
	@ResponseBody
	public FileIputResponse upload(MultipartHttpServletRequest request) {
		LOGGER.debug("uploadPost called");
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		List<Attachment> list = new LinkedList<>();

		Attachment attachment = new Attachment();
		//只处理第一个文件
		if (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			LOGGER.debug("Uploading {}", mpf.getOriginalFilename());

			String newFilenameBase = UUID.randomUUID().toString();
			String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf('.'));
			String newFilename = newFilenameBase + originalFileExtension;
			String storageDirectory = uploadPath;
			String contentType = mpf.getContentType();

			File newFile = new File(storageDirectory + File.separator + newFilename);
			try {
				mpf.transferTo(newFile);


				attachment.setName(mpf.getOriginalFilename());
				attachment.setType(contentType);
				attachment.setNewFileName(newFilename);
				attachment.setContentType(contentType);
				attachment.setSize(mpf.getSize());
				attachment.setDisabled(false);
				attachment.setCreateTime(new Date());
				attachementService.merge(attachment);


				list.add(attachment);
				LOGGER.info("{} store in path:{}",attachment.getName(), newFile.getAbsolutePath());
			} catch(IOException e) {
				LOGGER.error("Could not upload file "+mpf.getOriginalFilename(), e);
			}
		}

		return new FileIputResponse(attachment.getName(),  "120px", "common/attachment/"+attachment.getId(), 111, attachment.getId()+"");
	}


}
