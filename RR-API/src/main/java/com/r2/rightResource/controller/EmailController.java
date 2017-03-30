package com.r2.rightResource.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
import org.springframework.web.servlet.ModelAndView;

import com.r2.rightResource.model.Email;
import com.r2.rightResource.model.UserEmail;
import com.r2.rightResource.service.EmailReceiverService;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
	@Autowired
	EmailReceiverService emailService;

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public void receive(@PathVariable("host") String host, @PathVariable("port") String port,
			@PathVariable("user") String user, @PathVariable("pwd") String pwd) {
		emailService.downloadEmailAttachments(host, port, user, pwd);
	}

	@RequestMapping(value = "/receiveUI", method = RequestMethod.POST)
	public void receiveUI(@ModelAttribute("emailForm") UserEmail userEmail) {
		emailService.downloadEmailAttachments(userEmail.getHost(), userEmail.getPort(), userEmail.getUser(),
				userEmail.getPwd());
	}

	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public ModelAndView addUI() {
		ModelAndView model = new ModelAndView("emailform");
		model.addObject("emailForm", new UserEmail());
		return model;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Email> list() {
		return emailService.listEmail();
	}

	@RequestMapping(value = "/listUI", method = RequestMethod.GET)
	public ModelAndView listUI() {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.listEmail());
		return model;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public List<Email> delete(@PathVariable("id") String id) {
		Email email = emailService.findEmailById(id);
		emailService.delete(email);
		return emailService.listEmail();
	}

	@RequestMapping(value = "/deleteUI/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUI(@PathVariable("id") String id) {
		Email email = emailService.findEmailById(id);
		emailService.delete(email);
		return new ModelAndView("redirect:/email/listUI");
	}

	@RequestMapping(value = "/load/{name}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable("id") String id, @PathVariable("name") String name) {
		System.out.println("name:  " + name);
		Email email = emailService.findEmailById(id);
		Path path;
		if (name.equals("I")) {
			path = Paths.get(email.getAttachmentI());
		} else if (name.equals("II")) {
			path = Paths.get(email.getAttachmentII());
		} else if (name.equals("III")) {
			path = Paths.get(email.getAttachmentIII());
		} else {
			path = Paths.get(email.getAttachmentIV());
		}

		try {
			if (name.equals("I")) {
				java.nio.file.Files.write(path, email.getAttachment1());
			} else if (name.equals("II")) {
				java.nio.file.Files.write(path, email.getAttachment2());
			} else if (name.equals("III")) {
				java.nio.file.Files.write(path, email.getAttachment3());
			} else {
				java.nio.file.Files.write(path, email.getAttachment4());
			}

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

	@RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> subject(@PathVariable("id") String id) {
		Email email = emailService.findEmailById(id);
		Path path = Paths.get("Mail.html");

		try {
			java.nio.file.Files.write(path, email.getMessageContent().getBytes("utf-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);

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

	@RequestMapping(value = "/searchUI", method = RequestMethod.GET)
	public ModelAndView searchUI() {
		ModelAndView model = new ModelAndView("emailsearch");
		model.addObject("emailForm", new Email());
		return model;
	}

	@RequestMapping(value = "/from", method = RequestMethod.POST)
	public ModelAndView findEmailByFromAddr(@RequestParam("from") String from) {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.findEmailByFromAddr(from));
		return model;
	}

	@RequestMapping(value = "/to", method = RequestMethod.POST)
	public ModelAndView findEmailByToAddr(@RequestParam("to") String to) {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.findEmailByToAddr(to));
		return model;
	}

	@RequestMapping(value = "/subject", method = RequestMethod.POST)
	public ModelAndView findEmailBySubject(@RequestParam("subject") String subject) {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.findEmailBySubject(subject));
		return model;
	}

	@RequestMapping(value = "/sentDate", method = RequestMethod.POST)
	public ModelAndView findEmailByDate(@RequestParam("sentDate") String sentDate) {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.findEmailByDate(sentDate));
		return model;
	}

	@RequestMapping(value = "/messageContent", method = RequestMethod.POST)
	public ModelAndView findEmailByMessage(@RequestParam("messageContent") String messageContent) {
		ModelAndView model = new ModelAndView("emaillist");
		model.addObject("listEmail", emailService.findEmailByMessage(messageContent));
		return model;
	}

	@RequestMapping(value = "/from/{from}", method = RequestMethod.GET)
	public List<Email> findEmailByFromAddr2(@PathVariable("from") String from) {
		return emailService.findEmailByFromAddr(from);
	}

	@RequestMapping(value = "/to/{to}", method = RequestMethod.GET)
	public List<Email> findEmailByToAddr2(@PathVariable("to") String to) {
		return emailService.findEmailByToAddr(to);
	}

	@RequestMapping(value = "/subject/{subject}", method = RequestMethod.GET)
	public List<Email> findEmailBySubject2(@PathVariable("subject") String subject) {
		return emailService.findEmailBySubject(subject);
	}

	@RequestMapping(value = "/sentDate/{sentDate}", method = RequestMethod.GET)
	public List<Email> findEmailByDate2(@PathVariable("sentDate") String sentDate) {
		return emailService.findEmailByDate(sentDate);
	}

	@RequestMapping(value = "/messageContent/{messageContent}", method = RequestMethod.GET)
	public List<Email> findEmailByMessage2(@PathVariable("messageContent") String messageContent) {
		return emailService.findEmailByMessage(messageContent);
	}

}
