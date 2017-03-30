package com.r2.rightResource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.r2.rightResource.dao.CandidateDao;
import com.r2.rightResource.model.Candidate;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	CandidateDao candidateDao;

	@Override
	@CachePut("candidates")
	public List<Candidate> listCandidate() {
		return candidateDao.listCandidate();
	}

	@Override
	public void add(Candidate candidate) {
		refreshAllCandidates();
		candidateDao.add(candidate);
	}

	@Override
	public void update(Candidate candidate) {
		refreshAllCandidates();
		candidateDao.update(candidate);

	}

	@Override
	public void delete(Candidate candidate) {
		refreshAllCandidates();
		candidateDao.delete(candidate);

	}

	@Override
	public Candidate findCandidateById(String id) {
		return candidateDao.findCandidateById(id);
	}

	@Override
	public List<Candidate> findCandidateByName(String name) {
		return candidateDao.findCandidateByName(name);
	}

	@Override
	public List<Candidate> findCandidateByEmail(String email) {
		return candidateDao.findCandidateByEmail(email);
	}

	@Override
	public List<Candidate> findCandidateByPhone(String phone) {
		return candidateDao.findCandidateByPhone(phone);
	}

	@Override
	public List<Candidate> findCandidateByLocation(String location) {
		return candidateDao.findCandidateByLocation(location);
	}

	@Override
	public List<Candidate> findCandidateBySkills(String skills) {
		return candidateDao.findCandidateBySkills(skills);
	}

	@CacheEvict(value = "candidates", allEntries = true)
	public void refreshAllCandidates() {
		// This method will remove all 'candidates' from cache, say as a result
		// of flush-all API.
	}

}
