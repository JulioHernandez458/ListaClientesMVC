package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.models.service.IUploadFileService;


@SpringBootApplication
public class ClientListMvcApplication {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(ClientListMvcApplication.class, args);
	}
	
	
	public void run(String... args) throws Exception {
		try {
			uploadFileService.deleteAll();
			uploadFileService.init();
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
	}

}
