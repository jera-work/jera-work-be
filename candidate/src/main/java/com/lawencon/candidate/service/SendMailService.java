package com.lawencon.candidate.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lawencon.candidate.dto.email.EmailReqDto;


@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	
	private ExecutorService executorService;

	public void sendEmail(String emailTo, String subject, String body) {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(emailTo);
			msg.setSubject(subject);
			msg.setText(body);
			
			javaMailSender.send(msg);			
		});
	}

	public void sendCreateAccount(EmailReqDto email) {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {			
			MimeMessage msg = javaMailSender.createMimeMessage();
			
			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());
				
				final Context context = new Context();
				context.setVariable("data", email);
				final String htmlContent = templateEngine.process("create-account", context);
				helper.setText(htmlContent, true);
				
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK-CANDIDATE.jpg");
				final Resource imageFindJob = new ClassPathResource("/html-template/image/FINDING-JOB.jpg");

				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("imageJeraWork", imageFindJob, "image/jpeg");
				
				javaMailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
	}

	
}
