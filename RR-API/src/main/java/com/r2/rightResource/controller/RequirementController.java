package com.r2.rightResource.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.r2.rightResource.model.Requirement;
import com.r2.rightResource.service.RequirementService;

@RestController
@RequestMapping(value = "/requirement")
public class RequirementController {
	@Autowired
	RequirementService requirementService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Requirement> list() {
		return requirementService.listRequirement();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Requirement add() {
		return new Requirement();
	}

	@RequestMapping(value = "/listUI", method = RequestMethod.GET)
	public ModelAndView listUI() {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.listRequirement());
		return model;
	}

	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public ModelAndView addUI() {
		ModelAndView model = new ModelAndView("requirementform");
		model.addObject("requirementForm", new Requirement());
		return model;
	}

	@RequestMapping(value = "/searchUI", method = RequestMethod.GET)
	public ModelAndView searchUI() {
		ModelAndView model = new ModelAndView("requirementsearch");
		model.addObject("requirementForm", new Requirement());
		return model;
	}

	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable("id") String id) {

		Requirement requirement = requirementService.findRequirementById(id);

		Path path = Paths.get(requirement.getRequirementName());

		try {
			java.nio.file.Files.write(path, requirement.getRequirement());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resource file;
		try {
			file = new UrlResource(path.toUri());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public List<Requirement> update(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("requirementform");
		Requirement requirement = requirementService.findRequirementById(id);
		model.addObject("requirementForm", requirement);
		return requirementService.listRequirement();
	}

	@RequestMapping(value = "/updateUI/{id}", method = RequestMethod.GET)
	public ModelAndView updateUI(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("requirementform");
		Requirement requirement = requirementService.findRequirementById(id);
		model.addObject("requirementForm", requirement);
		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<Requirement> save(@ModelAttribute("requirementForm") Requirement requirement,
			@RequestParam("file") MultipartFile file) {

		try {
			requirement.setRequirementName(file.getOriginalFilename());
			requirement.setRequirement(file.getBytes());
		} catch (IOException e) {
			System.out.println("Not able to load file");
			e.printStackTrace();
		}
		if (requirement.getId() != null && !requirement.getId().trim().equals("")) {
			requirementService.update(requirement);
		} else {
			requirementService.add(requirement);
		}

		return requirementService.listRequirement();
	}

	@RequestMapping(value = "/saveUI", method = RequestMethod.POST)
	public ModelAndView saveUI(@ModelAttribute("requirementForm") Requirement requirement,
			@RequestParam("file") MultipartFile file) {

		try {
			requirement.setRequirementName(file.getOriginalFilename());
			requirement.setRequirement(file.getBytes());
		} catch (IOException e) {
			System.out.println("Not able to load file");
			e.printStackTrace();
		}
		if (requirement.getId() != null && !requirement.getId().trim().equals("")) {
			requirementService.update(requirement);
		} else {
			requirementService.add(requirement);
		}

		return new ModelAndView("redirect:/requirement/listUI");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public List<Requirement> delete(@PathVariable("id") String id) {
		Requirement requirement = requirementService.findRequirementById(id);
		requirementService.delete(requirement);
		return requirementService.listRequirement();
	}

	@RequestMapping(value = "/deleteUI/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUI(@PathVariable("id") String id) {
		Requirement requirement = requirementService.findRequirementById(id);
		requirementService.delete(requirement);
		return new ModelAndView("redirect:/requirement/listUI");
	}

	@RequestMapping(value = "/name", method = RequestMethod.POST)
	public ModelAndView findRequirementByName(@RequestParam("name") String name) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByName(name));
		return model;
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ModelAndView findRequirementByEmail(@RequestParam("email") String email) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByEmail(email));
		return model;
	}

	@RequestMapping(value = "/vendor", method = RequestMethod.POST)
	public ModelAndView findRequirementByVendor(@RequestParam("vendor") String vendor) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByVendor(vendor));
		return model;
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public ModelAndView findRequirementByClient(@RequestParam("client") String client) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByClient(client));
		return model;
	}

	@RequestMapping(value = "/phone", method = RequestMethod.POST)
	public ModelAndView findRequirementByPhone(@RequestParam("phone") String phone) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByPhone(phone));
		return model;
	}

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public ModelAndView findRequirementByLocation(@RequestParam("location") String location) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementByLocation(location));
		return model;
	}

	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public ModelAndView findRequirementBySkills(@RequestParam("skills") String skills) {
		ModelAndView model = new ModelAndView("requirementlist");
		model.addObject("listRequirement", requirementService.findRequirementBySkills(skills));
		return model;
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByName2(@PathVariable("name") String name) {
		return requirementService.findRequirementByName(name);
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByEmail2(@PathVariable("email") String email) {
		return requirementService.findRequirementByEmail(email);
	}

	@RequestMapping(value = "/vendor/{vendor}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByVendor2(@PathVariable("vendor") String vendor) {
		return requirementService.findRequirementByClient(vendor);
	}

	@RequestMapping(value = "/client/{client}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByClient2(@PathVariable("client") String client) {
		return requirementService.findRequirementByClient(client);
	}

	@RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByPhone2(@PathVariable("phone") String phone) {
		return requirementService.findRequirementByPhone(phone);
	}

	@RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
	public List<Requirement> findRequirementByLocation2(@PathVariable("location") String location) {
		return requirementService.findRequirementByLocation(location);
	}

	@RequestMapping(value = "/skills/{skills}", method = RequestMethod.GET)
	public List<Requirement> findRequirementBySkills2(@PathVariable("skills") String skills) {
		return requirementService.findRequirementBySkills(skills);
	}
}
