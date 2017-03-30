package com.r2.rightResource.service;

import java.util.List;

import com.r2.rightResource.model.Email;

public interface EmailReceiverService {

	public void downloadEmailAttachments(String host, String port, String userName, String password);

	public List<Email> listEmail();

	public Email findEmailById(String id);

	public void delete(Email email);

	public List<Email> findEmailByFromAddr(String from);

	public List<Email> findEmailByToAddr(String to);

	public List<Email> findEmailBySubject(String subject);

	public List<Email> findEmailByDate(String sentDate);

	public List<Email> findEmailByMessage(String messageContent);
}
