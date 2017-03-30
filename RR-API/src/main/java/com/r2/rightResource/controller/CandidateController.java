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

import com.r2.rightResource.model.Candidate;
import com.r2.rightResource.service.CandidateService;

@RestController
@RequestMapping(value = "/candidate")
public class CandidateController {
	@Autowired
	CandidateService candidateService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Candidate> list() {
		return candidateService.listCandidate();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Candidate add() {
		return new Candidate();
	}

	@RequestMapping(value = "/listUI", method = RequestMethod.GET)
	public ModelAndView listUI() {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.listCandidate());
		return model;
	}

	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public ModelAndView addUI() {
		ModelAndView model = new ModelAndView("candidateform");
		model.addObject("candidateForm", new Candidate());
		return model;
	}

	@RequestMapping(value = "/searchUI", method = RequestMethod.GET)
	public ModelAndView searchUI() {
		ModelAndView model = new ModelAndView("candidatesearch");
		model.addObject("candidateForm", new Candidate());
		return model;
	}

	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable("id") String id) {

		Candidate candidate = candidateService.findCandidateById(id);

		Path path = Paths.get(candidate.getResumeName());

		try {
			java.nio.file.Files.write(path, candidate.getResume());
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
	public List<Candidate> update(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("candidateform");
		Candidate candidate = candidateService.findCandidateById(id);
		model.addObject("candidateForm", candidate);
		return candidateService.listCandidate();
	}

	@RequestMapping(value = "/updateUI/{id}", method = RequestMethod.GET)
	public ModelAndView updateUI(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView("candidateform");
		Candidate candidate = candidateService.findCandidateById(id);
		model.addObject("candidateForm", candidate);
		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<Candidate> save(@ModelAttribute("candidateForm") Candidate candidate,
			@RequestParam("file") MultipartFile file) {

		try {
			candidate.setResumeName(file.getOriginalFilename());
			candidate.setResume(file.getBytes());
		} catch (IOException e) {
			System.out.println("Not able to load file");
			e.printStackTrace();
		}
		if (candidate.getId() != null && !candidate.getId().trim().equals("")) {
			candidateService.update(candidate);
		} else {
			candidateService.add(candidate);
		}

		return candidateService.listCandidate();
	}

	@RequestMapping(value = "/saveUI", method = RequestMethod.POST)
	public ModelAndView saveUI(@ModelAttribute("candidateForm") Candidate candidate,
			@RequestParam("file") MultipartFile file) {

		try {
			candidate.setResumeName(file.getOriginalFilename());
			candidate.setResume(file.getBytes());
		} catch (IOException e) {
			System.out.println("Not able to load file");
			e.printStackTrace();
		}
		if (candidate.getId() != null && !candidate.getId().trim().equals("")) {
			candidateService.update(candidate);
		} else {
			candidateService.add(candidate);
		}
		return new ModelAndView("redirect:/candidate/listUI");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public List<Candidate> delete(@PathVariable("id") String id) {
		Candidate candidate = candidateService.findCandidateById(id);
		candidateService.delete(candidate);
		return candidateService.listCandidate();
	}

	@RequestMapping(value = "/deleteUI/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUI(@PathVariable("id") String id) {
		Candidate candidate = candidateService.findCandidateById(id);
		candidateService.delete(candidate);
		return new ModelAndView("redirect:/candidate/listUI");
	}

	@RequestMapping(value = "/name", method = RequestMethod.POST)
	public ModelAndView findCandidateByName(@RequestParam("fName") String name) {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.findCandidateByName(name));
		return model;
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ModelAndView findCandidateByEmail(@RequestParam("email") String email) {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.findCandidateByEmail(email));
		return model;
	}

	@RequestMapping(value = "/phone", method = RequestMethod.POST)
	public ModelAndView findCandidateByPhone(@RequestParam("phone") String phone) {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.findCandidateByPhone(phone));
		return model;
	}

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public ModelAndView findCandidateByLocation(@RequestParam("location") String location) {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.findCandidateByLocation(location));
		return model;
	}

	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public ModelAndView findCandidateBySkills(@RequestParam("skills") String skills) {
		ModelAndView model = new ModelAndView("candidatelist");
		model.addObject("listCandidate", candidateService.findCandidateBySkills(skills));
		return model;
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Candidate> findCandidateByName2(@PathVariable("name") String name) {
		return candidateService.findCandidateByName(name);
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public List<Candidate> findCandidateByEmail2(@PathVariable("email") String email) {
		return candidateService.findCandidateByEmail(email);
	}

	@RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
	public List<Candidate> findCandidateByPhone2(@PathVariable("phone") String phone) {
		return candidateService.findCandidateByPhone(phone);
	}

	@RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
	public List<Candidate> findCandidateByLocation2(@PathVariable("location") String location) {
		return candidateService.findCandidateByLocation(location);
	}

	@RequestMapping(value = "/skills/{skills}", method = RequestMethod.GET)
	public List<Candidate> findCandidateBySkills2(@PathVariable("skills") String skills) {
		return candidateService.findCandidateBySkills(skills);
	}
}
