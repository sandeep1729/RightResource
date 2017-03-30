package com.r2.rightResource.dao;

import java.util.List;

import com.r2.rightResource.model.Email;

public interface EmailDao {

	public void add(List<Email> emails);

	public List<Email> listEmail();

	public Email findEmailById(String id);

	public void delete(Email email);

	public List<Email> findEmailByFromAddr(String from);

	public List<Email> findEmailByToAddr(String to);

	public List<Email> findEmailBySubject(String subject);

	public List<Email> findEmailByDate(String sentDate);

	public List<Email> findEmailByMessage(String messageContent);
}
