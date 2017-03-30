package com.r2.rightResource.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.r2.rightResource.model.Candidate;

@Repository
public class CandidateDaoImpl implements CandidateDao {

	@Autowired
	MongoTemplate mongoTemplate;

	private static final String COLLECTION_NAME = "candidate";

	@Override
	public List<Candidate> listCandidate() {
		Query query = new Query();
		query.fields().exclude("resume");
		return mongoTemplate.find(query, Candidate.class, COLLECTION_NAME);
	}

	@Override
	public void add(Candidate candidate) {
		if (!mongoTemplate.collectionExists(Candidate.class)) {
			mongoTemplate.createCollection(Candidate.class);
		}
		candidate.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(candidate, COLLECTION_NAME);

	}

	@Override
	public void update(Candidate candidate) {
		mongoTemplate.save(candidate);

	}

	@Override
	public void delete(Candidate candidate) {
		mongoTemplate.remove(candidate, COLLECTION_NAME);

	}

	@Override
	public Candidate findCandidateById(String id) {
		return mongoTemplate.findById(id, Candidate.class);
	}

	@Override
	public List<Candidate> findCandidateByName(String name) {

		List<Candidate> candidates;
		Query query = new Query();
		// query.fields().exclude("resume");
		query.addCriteria(Criteria.where("lName").regex(name, "i"));

		candidates = mongoTemplate.find(query, Candidate.class);
		if (candidates.size() > 0) {
			return candidates;
		}
		query = new Query();
		query.addCriteria(Criteria.where("fName").regex(name, "i"));

		return mongoTemplate.find(query, Candidate.class);
	}

	@Override
	public List<Candidate> findCandidateByEmail(String email) {

		Query query = new Query();
		query.addCriteria(Criteria.where("email").regex(email, "i"));
		return mongoTemplate.find(query, Candidate.class);
	}

	@Override
	public List<Candidate> findCandidateByPhone(String phone) {

		Query query = new Query();
		query.addCriteria(Criteria.where("phone").regex(phone, "i"));
		return mongoTemplate.find(query, Candidate.class);
	}

	@Override
	public List<Candidate> findCandidateByLocation(String location) {

		Query query = new Query();
		query.addCriteria(Criteria.where("location").regex(location, "i"));
		return mongoTemplate.find(query, Candidate.class);
	}

	@Override
	public List<Candidate> findCandidateBySkills(String skills) {

		Query query = new Query();
		query.addCriteria(Criteria.where("skills").regex(skills, "i"));
		return mongoTemplate.find(query, Candidate.class);
	}
}
