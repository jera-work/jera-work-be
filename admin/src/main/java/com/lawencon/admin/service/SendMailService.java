package com.lawencon.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

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

import com.lawencon.admin.dto.email.EmailReqDto;
import com.lawencon.admin.dto.email.OfferingReqDto;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private TemplateEngine templateEngine;

	public void sendEmail(String emailTo, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(emailTo);
		msg.setSubject(subject);
		msg.setText(body);

		javaMailSender.send(msg);
	}
	
	public void sendOffering(EmailReqDto email, OfferingReqDto data, byte[] file)  throws MessagingException, IOException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(email.getEmail());
		helper.setSubject(email.getSubject());
		
		final Context context = new Context();
        context.setVariable("data", data);
        final String htmlContent = templateEngine.process("offering-letter", context);
        helper.setText(htmlContent, true);
        
        byte[] decodedBytes = Base64.getDecoder().decode(data.getCompanyPhoto());
        final String pdfPath = "offering-letter.pdf";
        try {
        	File outputPdf = new File(pdfPath);
            FileOutputStream fos = new FileOutputStream(outputPdf);
            fos.write(file);
            fos.close();
            
            final Resource imageJeraWork = new ClassPathResource("/html-template/image/JERA-WORK.jpg");

            helper.addInline("logo", imageJeraWork, "image/jpeg");
            helper.addInline("logocompany", new ByteArrayDataSource(decodedBytes, "image/png"));
            
            helper.addAttachment(pdfPath, outputPdf);
            System.out.println("Byte array has been written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.javaMailSender.send(msg);
	}

}
