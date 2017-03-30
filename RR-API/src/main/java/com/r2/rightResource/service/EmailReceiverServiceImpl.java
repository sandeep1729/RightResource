package com.r2.rightResource.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2.rightResource.dao.EmailDao;
import com.r2.rightResource.model.Email;

@Service
public class EmailReceiverServiceImpl implements EmailReceiverService {

	@Autowired
	EmailDao emailDao;

	private List<Email> emails = new ArrayList<>();
	private Email email = new Email();
	private int count;

	/**
	 * Downloads new messages and saves attachments to disk if any.
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 */
	public void downloadEmailAttachments(String host, String port, String userName, String password) {

		Properties properties = new Properties();

		// server setting
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);

		// SSL setting
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("pop3");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				email = new Email();
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				String from = fromAddress[0].toString();
				String subject = message.getSubject();
				String sentDate = message.getSentDate().toString();

				String contentType = message.getContentType();
				String messageContent = "";
				StringBuilder sb = new StringBuilder();
				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							try {
								if (partCount == 0) {
									email.setAttachment1(IOUtils.toByteArray(part.getInputStream()));
									email.setAttachmentI(fileName);
								} else if (partCount == 1) {
									email.setAttachment2(IOUtils.toByteArray(part.getInputStream()));
									email.setAttachmentII(fileName);
								} else if (partCount == 2) {
									email.setAttachment3(IOUtils.toByteArray(part.getInputStream()));
									email.setAttachmentIII(fileName);
								} else if (partCount == 3) {
									email.setAttachment4(IOUtils.toByteArray(part.getInputStream()));
									email.setAttachmentIV(fileName);
								} else {
									email.setAttachment1(IOUtils.toByteArray(part.getInputStream()));
									email.setAttachmentI(fileName);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						} else {
							// this part may be the message content

							if (part.getContent() instanceof MimeMultipart) {
								MimeMultipart mimeMultipart = (MimeMultipart) part.getContent();
								for (int j = 0; j < mimeMultipart.getCount(); j++) {
									sb.append(mimeMultipart.getBodyPart(j).getContent().toString());

								}
								messageContent = sb.toString();
							} else {
								messageContent = part.getContent().toString();
							}

						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}
				email.setTo(userName);
				email.setFrom(from);
				email.setSubject(subject);
				email.setSentDate(sentDate);
				email.setMessageContent(messageContent);
				emails.add(email);
				if (count == 15) {
					emailDao.add(emails);
					emails = new ArrayList<>();
					count = 0;
					continue;
				}
				count++;
			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Email> listEmail() {
		return emailDao.listEmail();
	}

	@Override
	public Email findEmailById(String id) {
		return emailDao.findEmailById(id);
	}

	@Override
	public void delete(Email email) {
		emailDao.delete(email);

	}

	@Override
	public List<Email> findEmailByFromAddr(String from) {
		return emailDao.findEmailByFromAddr(from);
	}

	@Override
	public List<Email> findEmailByToAddr(String to) {
		return emailDao.findEmailByToAddr(to);
	}

	@Override
	public List<Email> findEmailBySubject(String subject) {
		return emailDao.findEmailBySubject(subject);
	}

	@Override
	public List<Email> findEmailByDate(String sentDate) {
		return emailDao.findEmailByDate(sentDate);
	}

	@Override
	public List<Email> findEmailByMessage(String messageContent) {
		return emailDao.findEmailByMessage(messageContent);
	}

}
