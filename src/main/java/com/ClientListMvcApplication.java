package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.models.service.IUploadFileService;


@SpringBootApplication
public class ClientListMvcApplication implements CommandLineRunner{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	public static void main(String[] args) {
		SpringApplication.run(ClientListMvcApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		try {
			//uploadFileService.deleteAll();
			uploadFileService.init();
			
			// Password generator
			String password = "12345"; // Password
			for(int i=0; i<2; i++) {
				String bcryptPassword = passwordEncoder.encode(password);
				log.info("Password Generated: " + bcryptPassword);
			}
			
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
	}

}
