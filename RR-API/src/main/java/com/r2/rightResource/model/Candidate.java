package com.r2.rightResource.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String fName;
	private String lName;
	private String email;
	private String phone;
	private String location;
	private String resumeName;
	private String skills;
	private byte[] resume;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

}
