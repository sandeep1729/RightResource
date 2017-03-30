package com.r2.rightResource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.r2.rightResource.dao.RequirementDao;
import com.r2.rightResource.model.Requirement;

@Service
public class RequirementServiceImpl implements RequirementService {

	@Autowired
	RequirementDao requirementDao;

	@Override
	@CachePut("requirements")
	public List<Requirement> listRequirement() {
		return requirementDao.listRequirement();
	}

	@Override
	public void add(Requirement requirement) {
		refreshAllRequirements();
		requirementDao.add(requirement);
	}

	@Override
	public void update(Requirement requirement) {
		refreshAllRequirements();
		requirementDao.update(requirement);
	}

	@Override
	public void delete(Requirement requirement) {
		refreshAllRequirements();
		requirementDao.delete(requirement);

	}

	@Override
	public Requirement findRequirementById(String id) {
		return requirementDao.findRequirementById(id);
	}

	@Override
	public List<Requirement> findRequirementByName(String name) {
		return requirementDao.findRequirementByName(name);
	}

	@Override
	public List<Requirement> findRequirementByEmail(String email) {
		return requirementDao.findRequirementByEmail(email);
	}

	@Override
	public List<Requirement> findRequirementByVendor(String vendor) {
		return requirementDao.findRequirementByVendor(vendor);
	}

	@Override
	public List<Requirement> findRequirementByClient(String client) {
		return requirementDao.findRequirementByClient(client);
	}

	@Override
	public List<Requirement> findRequirementByPhone(String phone) {
		return requirementDao.findRequirementByPhone(phone);
	}

	@Override
	public List<Requirement> findRequirementByLocation(String location) {
		return requirementDao.findRequirementByLocation(location);
	}

	@Override
	public List<Requirement> findRequirementBySkills(String skills) {
		return requirementDao.findRequirementBySkills(skills);
	}

	@CacheEvict(value = "requirements", allEntries = true)
	public void refreshAllRequirements() {
		// This method will remove all 'requirements' from cache, say as a
		// result
		// of flush-all API.
	}

}
