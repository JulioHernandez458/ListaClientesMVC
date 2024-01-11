package com.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathPhoto = getPath(filename);
		log.info("pathPhoto: " + pathPhoto);

		Resource rs = new UrlResource(pathPhoto.toUri());

		if (!rs.exists() || !rs.isReadable()) {
			throw new RuntimeException("Error: The Image Can´t Be Uploaded: " + pathPhoto.toString());
		}
		return rs;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		
		String uniqueFilename = UUID.randomUUID().toString().substring(0, 5) + "-" + file.getOriginalFilename();
		try {
			Path rootPath = getPath(uniqueFilename);

			log.info("rootPath: " + rootPath);
			Files.copy(file.getInputStream(), rootPath);
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		try {
			Path rootPath = getPath(filename);
			File file = rootPath.toFile();

			if (file.exists() && file.canRead()) {
				if (file.delete()) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
		
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}

	}

	@Override
	public void init() throws IOException {
		try {
			Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
	}

}
