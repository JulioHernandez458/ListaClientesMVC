package com.controllers;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.models.entity.Client;
import com.models.service.IClientService;
import com.models.service.IUploadFileService;


@Controller
@SessionAttributes("client")
//@RequestMapping("/clients")
public class ClientController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IClientService service;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	@Secured("ROLE_USER")
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> showPhoto(@PathVariable String filename) throws MalformedURLException {

		Resource rs = null;

		try {
			rs = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			log.error(e.toString());
			throw e;
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rs.getFilename() + "\"")
				.body(rs);
	}
	
	
	
	@GetMapping({"/",""})
	public String getClients(Model model, Authentication authentication, HttpServletRequest request) throws Throwable {
		
		if(authentication != null)
			log.info(authentication.getName() + ", Logged in...OK");
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(hasRole("ROLE_ADMIN")) {
			log.info(auth.getName().concat(", Access Granted!!"));
		} else {
			log.info(auth.getName().concat(", Access Denied!!"));
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "");
		
		if(securityContext.isUserInRole("ROLE_ADMIN")) {
			log.info("Using SecurityContextHolderAwareRequestWrapper: ".concat(auth.getName()).concat(", Access Granted!!"));
		} else {
			log.info("Using SecurityContextHolderAwareRequestWrapper: ".concat(auth.getName()).concat(", Access Denied!!"));
		}

		if(request.isUserInRole("ROLE_ADMIN")) {
			log.info("Using HttpServletRequest: ".concat(auth.getName()).concat(", Access Granted!!"));
		} else {
			log.info("Using HttpServletRequest: ".concat(auth.getName()).concat(", Access Denied!"));
		}	
		
			
		List<Client> clients = service.findAll();
		
		model.addAttribute("title", "Clients List");
		model.addAttribute("clients", clients);
		return "home";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/show/{id}")
	public String showClient(@PathVariable String id, Model model, RedirectAttributes flash) {

		log.info("LOOKING FOR CLIENT...");
		Client client = service.findOne(id);
		if (client == null) {
			log.info("cLIENT NOT FOUND...");
			flash.addFlashAttribute("error", "Client Not Exists!");
			return "redirect:/";
		}
		
		
		log.info("CLIENT FOUND...OK");
		model.addAttribute("client", client);
		model.addAttribute("title", "Client: " + client.getName());
		return "show";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/form")
	public String getForm(Model model) {

		model.addAttribute("client", new Client());
		model.addAttribute("title", "New Client Form");
		return "form";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/form/{id}")
	public String editClient(@PathVariable String id, Model model, RedirectAttributes flash) {

		Client client = null;

		if (!id.isEmpty()) {
			log.info("LOOKING FOR CLIENT...");
			client = service.findOne(id);
			if (client == null) {
				log.info("CLIENT NOT FOUND...");
				flash.addFlashAttribute("error", "Client ID Not Exists!");
				return "redirect:/";
			}
		} else {
			log.info("CLIENT ID IS EMPTY...");
			flash.addFlashAttribute("error", "ID Must Not Be Empty!");
			return "redirect:/";
		}
		log.info("CLIENT FOUND...OK");
		model.addAttribute("client", client);
		model.addAttribute("title", "Edit Client");
		return "form";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/form")
	public String postClient(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) throws Throwable {
		
		try {
			
			if (result.hasErrors()) {
				model.addAttribute("title", "New Client Form");
				return "form";
			}
			
			if (!photo.isEmpty()) {

				if (client.getId() != null && !client.getId().isEmpty() && client.getPhoto() != null
						&& client.getPhoto().length() > 0) {
					uploadFileService.delete(client.getPhoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(photo);
				} catch (IOException e) {
					log.info(e.toString());
					throw e;
				}

				//flash.addFlashAttribute("info", "You've uploaded '" + uniqueFilename + "'");
				client.setPhoto(uniqueFilename);
			}
			
			String messageFlash = (client.getId() != null) ? "Client Has Been Updated!" : "Client Has Been Created!";
			
			service.save(client);
			status.setComplete();
			flash.addFlashAttribute("success", messageFlash);
			model.addAttribute("title", "New Client Form");
			
		} catch (Throwable e) {
			log.error(e.toString());
			throw e;
		}
		
		return "redirect:/";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable String id, Model model, RedirectAttributes flash) {
		
		try {
			
			Client client = null;

			if (!id.isEmpty()) {
				log.info("LOOKING FOR CLIENT...");
				client = service.findOne(id);
				if (client != null) {
					log.info("CLIENT FOUND...OK");
					service.delete(id);
					log.info("CLIENT DELETED...OK");
					flash.addFlashAttribute("success", "Client Has Been Deleted!");
					if(client.getPhoto() != null) {
						if (uploadFileService.delete(client.getPhoto())) {
							flash.addFlashAttribute("info", "Photo " + client.getPhoto() + " Deleted!");
						}
					}
				}
			} else {
				log.info("CLIENT ID IS EMPTY...");
				flash.addFlashAttribute("error", "ID Must Not Be Empty!");
			}
			
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
		
		return "redirect:/";
		
	}
	
	
	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			if (role.equals(authority.getAuthority())) {
				log.info("User: ".concat(auth.getName())
						.concat(", Role: ".concat(authority.getAuthority())));
				return true;
			}
		}

		return false;

	}

}
