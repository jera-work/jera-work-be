package com.lawencon.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lawencon.admin.dto.email.AssessmentVacancyReqDto;
import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.HiredEmployeeReqDto;
import com.lawencon.admin.dto.email.InterviewVacancyReqDto;
import com.lawencon.admin.dto.email.McuVacancyReqDto;
import com.lawencon.admin.dto.email.OfferingReqDto;

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

	public void sendOffering(EmailReqDto email, OfferingReqDto data, byte[] file) throws MessagingException, IOException {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {			
			MimeMessage msg = javaMailSender.createMimeMessage();
			
			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());
				
				final Context context = new Context();
				context.setVariable("data", data);
				final String htmlContent = templateEngine.process("offering-letter", context);
				helper.setText(htmlContent, true);
				
				byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
				final String pdfPath = "offering-letter.pdf";
				File outputPdf = new File(pdfPath);
				FileOutputStream fos = new FileOutputStream(outputPdf);
				fos.write(file);
				fos.close();
				
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");
				
				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));
				
				helper.addAttachment(pdfPath, outputPdf);

				javaMailSender.send(msg);
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		});

	}

	public void sendAssessment(EmailReqDto email, AssessmentVacancyReqDto data) throws MessagingException {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {			
			MimeMessage msg = javaMailSender.createMimeMessage();
			
			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());
				
				final Context context = new Context();
				context.setVariable("data", data);
				if(data.getUrl() != null) {
					final String htmlContent = templateEngine.process("assessment-test", context);					
					helper.setText(htmlContent, true);
				} else {
					final String htmlContent = templateEngine.process("assessment-test", context);
					helper.setText(htmlContent, true);
				}
				
				byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");
				
				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));
				
				javaMailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
	}

	public void sendInterview(EmailReqDto email, InterviewVacancyReqDto data) throws MessagingException, IOException {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {
			MimeMessage msg = javaMailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());

				final Context context = new Context();
				context.setVariable("data", data);
				final String htmlContent = templateEngine.process("interview", context);
				helper.setText(htmlContent, true);

				byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");

				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
	}

	public void sendMcu(EmailReqDto email, McuVacancyReqDto data) throws MessagingException, IOException {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {
			MimeMessage msg = javaMailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());

				final Context context = new Context();
				context.setVariable("data", data);
				final String htmlContent = templateEngine.process("mcu", context);
				helper.setText(htmlContent, true);

				byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");

				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});

	}

	public void sendHired(EmailReqDto email, HiredEmployeeReqDto data) throws MessagingException, IOException {
		executorService = Executors.newFixedThreadPool(8);
		executorService.execute(() -> {
			MimeMessage msg = javaMailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(email.getEmail());
				helper.setSubject(email.getSubject());

				final Context context = new Context();
				context.setVariable("data", data);
				final String htmlContent = templateEngine.process("hired", context);
				helper.setText(htmlContent, true);

				byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
				final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");

				helper.addInline("logo", imageJeraWork, "image/jpeg");
				helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
	}
}
