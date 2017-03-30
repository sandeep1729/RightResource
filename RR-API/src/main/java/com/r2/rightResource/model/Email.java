package com.r2.rightResource.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String from;
	private String to;
	private String subject;
	private String sentDate;
	private String messageContent;
	private byte[] attachment1;
	private byte[] attachment2;
	private byte[] attachment3;
	private byte[] attachment4;
	private String attachmentI;
	private String attachmentII;
	private String attachmentIII;
	private String attachmentIV;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSentDate() {
		return sentDate;
	}

	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public byte[] getAttachment1() {
		return attachment1;
	}

	public void setAttachment1(byte[] attachment1) {
		this.attachment1 = attachment1;
	}

	public byte[] getAttachment2() {
		return attachment2;
	}

	public void setAttachment2(byte[] attachment2) {
		this.attachment2 = attachment2;
	}

	public byte[] getAttachment3() {
		return attachment3;
	}

	public void setAttachment3(byte[] attachment3) {
		this.attachment3 = attachment3;
	}

	public byte[] getAttachment4() {
		return attachment4;
	}

	public void setAttachment4(byte[] attachment4) {
		this.attachment4 = attachment4;
	}

	public String getAttachmentI() {
		return attachmentI;
	}

	public void setAttachmentI(String attachmentI) {
		this.attachmentI = attachmentI;
	}

	public String getAttachmentII() {
		return attachmentII;
	}

	public void setAttachmentII(String attachmentII) {
		this.attachmentII = attachmentII;
	}

	public String getAttachmentIII() {
		return attachmentIII;
	}

	public void setAttachmentIII(String attachmentIII) {
		this.attachmentIII = attachmentIII;
	}

	public String getAttachmentIV() {
		return attachmentIV;
	}

	public void setAttachmentIV(String attachmentIV) {
		this.attachmentIV = attachmentIV;
	}
}
