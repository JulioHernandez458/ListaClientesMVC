package com.controllers;




import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.models.entity.Client;
import com.models.service.IClientService;

@Controller
@SessionAttributes("client")
public class ClientController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IClientService service;
	
	
	@GetMapping({"","/"})
	public String getClients(Model model) throws Throwable {
		
		model.addAttribute("title", "Client List");
		model.addAttribute("clients", service.findAll());
		return "home";
	}
	
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
	
	@GetMapping(value = "/form")
	public String getForm(Model model) {

		model.addAttribute("client", new Client());
		model.addAttribute("titulo", "New Client Form");
		return "form";
	}
	
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
	
	@PostMapping("/form")
	public String postClient(@Valid Client client, BindingResult result, Model model,
			/*@RequestParam("file") MultipartFile photo,*/ RedirectAttributes flash, SessionStatus status) {
		
		try {
			
			if (result.hasErrors()) {
				model.addAttribute("title", "New Client Form");
				return "form";
			}
			
			String messageFlash = (client.getId() != null) ? "Client Has Been Updated!" : "Client Has Been Created!";
			
			service.save(client);
			status.setComplete();
			flash.addFlashAttribute("success", messageFlash);
			model.addAttribute("title", "New Client Form");
			
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
		
		return "redirect:/";
	}
	
	
	
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
}
