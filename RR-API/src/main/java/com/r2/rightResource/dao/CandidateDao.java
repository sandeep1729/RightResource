package com.r2.rightResource.dao;

import java.util.List;

import com.r2.rightResource.model.Candidate;

public interface CandidateDao {

	public List<Candidate> listCandidate();

	public void add(Candidate candidate);

	public void update(Candidate candidate);

	public void delete(Candidate candidate);

	public Candidate findCandidateById(String id);

	public List<Candidate> findCandidateByName(String name);

	public List<Candidate> findCandidateByEmail(String email);

	public List<Candidate> findCandidateByPhone(String phone);

	public List<Candidate> findCandidateByLocation(String location);

	public List<Candidate> findCandidateBySkills(String skills);

}
