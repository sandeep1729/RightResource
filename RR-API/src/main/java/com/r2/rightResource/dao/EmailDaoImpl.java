package com.r2.rightResource.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.r2.rightResource.model.Email;

@Repository
public class EmailDaoImpl implements EmailDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "email";

	@Override
	public void add(List<Email> emails) {
		mongoTemplate.insert(emails, COLLECTION_NAME);

	}

	@Override
	public List<Email> listEmail() {
		Query query = new Query();
		query.fields().exclude("attachment1");
		query.fields().exclude("attachment2");
		query.fields().exclude("attachment3");
		query.fields().exclude("attachment4");
		query.fields().exclude("messageContent");
		return mongoTemplate.find(query, Email.class, COLLECTION_NAME);
	}

	@Override
	public Email findEmailById(String id) {
		return mongoTemplate.findById(id, Email.class);
	}

	@Override
	public void delete(Email email) {
		mongoTemplate.remove(email, COLLECTION_NAME);
	}

	@Override
	public List<Email> findEmailByFromAddr(String from) {
		Query query = new Query();
		query.addCriteria(Criteria.where("from").regex(from, "i"));

		return mongoTemplate.find(query, Email.class);
	}

	@Override
	public List<Email> findEmailByToAddr(String to) {
		Query query = new Query();
		query.addCriteria(Criteria.where("to").regex(to, "i"));

		return mongoTemplate.find(query, Email.class);
	}

	@Override
	public List<Email> findEmailBySubject(String subject) {
		Query query = new Query();
		query.addCriteria(Criteria.where("subject").regex(subject, "i"));

		return mongoTemplate.find(query, Email.class);
	}

	@Override
	public List<Email> findEmailByDate(String sentDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sentDate").regex(sentDate, "i"));

		return mongoTemplate.find(query, Email.class);
	}

	@Override
	public List<Email> findEmailByMessage(String messageContent) {
		Query query = new Query();
		query.addCriteria(Criteria.where("messageContent").regex(messageContent, "i"));

		return mongoTemplate.find(query, Email.class);
	}

}
