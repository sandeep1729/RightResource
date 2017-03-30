package com.r2.rightResource.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.r2.rightResource.model.Requirement;

@Repository
public class RequirementDaoImpl implements RequirementDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "requirement";

	@Override
	public List<Requirement> listRequirement() {
		Query query = new Query();
		query.fields().exclude("requirement");
		return mongoTemplate.find(query, Requirement.class, COLLECTION_NAME);
	}

	@Override
	public void add(Requirement requirement) {
		if (!mongoTemplate.collectionExists(Requirement.class)) {
			mongoTemplate.createCollection(Requirement.class);
		}
		requirement.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(requirement, COLLECTION_NAME);

	}

	@Override
	public void update(Requirement requirement) {
		mongoTemplate.save(requirement);

	}

	@Override
	public void delete(Requirement requirement) {
		mongoTemplate.remove(requirement, COLLECTION_NAME);

	}

	@Override
	public Requirement findRequirementById(String id) {
		return mongoTemplate.findById(id, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByName(String name) {

		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name, "i"));

		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByEmail(String email) {

		Query query = new Query();
		query.addCriteria(Criteria.where("email").regex(email, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByPhone(String phone) {

		Query query = new Query();
		query.addCriteria(Criteria.where("phone").regex(phone, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByVendor(String vendor) {

		Query query = new Query();
		query.addCriteria(Criteria.where("vendor").regex(vendor, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByClient(String client) {

		Query query = new Query();
		query.addCriteria(Criteria.where("client").regex(client, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementByLocation(String location) {

		Query query = new Query();
		query.addCriteria(Criteria.where("location").regex(location, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

	@Override
	public List<Requirement> findRequirementBySkills(String skills) {

		Query query = new Query();
		query.addCriteria(Criteria.where("skills").regex(skills, "i"));
		return mongoTemplate.find(query, Requirement.class);
	}

}
