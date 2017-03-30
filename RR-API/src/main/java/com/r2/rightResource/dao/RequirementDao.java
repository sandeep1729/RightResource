package com.r2.rightResource.dao;

import java.util.List;

import com.r2.rightResource.model.Requirement;

public interface RequirementDao {

	public List<Requirement> listRequirement();

	public void add(Requirement requirement);

	public void update(Requirement requirement);

	public void delete(Requirement requirement);

	public Requirement findRequirementById(String id);

	public List<Requirement> findRequirementByName(String name);

	public List<Requirement> findRequirementByEmail(String email);

	public List<Requirement> findRequirementByVendor(String vendor);

	public List<Requirement> findRequirementByClient(String client);

	public List<Requirement> findRequirementByPhone(String phone);

	public List<Requirement> findRequirementByLocation(String location);

	public List<Requirement> findRequirementBySkills(String skills);

}
