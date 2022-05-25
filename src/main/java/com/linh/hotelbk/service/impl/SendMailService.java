package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.dto.response.EmailBookingDTO;
import com.linh.hotelbk.repository.IBookingFormRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class SendMailService {
    private final JavaMailSender mailSender;
    private final Configuration configuration;
    private final IBookingFormRepository bookingFormRepository;

    @Async("HotelBKExecutor")
    public void sendBookingMail(EmailBookingDTO request, EmailBookingDTO.ContentEmail model, Long id){
        log.info("Execute method with configured executor - "+ Thread.currentThread().getName());
        log.info("sending email");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            ClassPathResource image = new ClassPathResource("static/file/logo.png");
            ClassPathResource imageBanner = new ClassPathResource("static/file/welcome.jpg");
            ClassPathResource facebook = new ClassPathResource("static/file/facebook.jpg");
            ClassPathResource twitter = new ClassPathResource("static/file/twitter.jpg");
            Template template = configuration.getTemplate("emailRegistration.tfl");
            Map<String, Object> input = new HashMap<>();
            input.put("title", "Đặt phòng thành công !");
            input.put("model", model);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, input);
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            helper.addInline("logo", image);
            helper.addInline("welcome", imageBanner);
            helper.addInline("facebook", facebook);
            helper.addInline("twitter", twitter);
            mailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            bookingFormRepository.deleteById(id);
            e.printStackTrace();
        }
    }

    @Async("HotelBKExecutor")
    public void sendResetPassMail(String resetPassToken, EmailBookingDTO request){
        log.info("Execute method with configured executor - "+ Thread.currentThread().getName());
        log.info("sending email");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            ClassPathResource image = new ClassPathResource("static/file/logo.png");
            ClassPathResource facebook = new ClassPathResource("static/file/facebook.jpg");
            ClassPathResource twitter = new ClassPathResource("static/file/twitter.jpg");
            Template template = configuration.getTemplate("mailResetPassword.tfl");
            Map<String, Object> input = new HashMap<>();
            input.put("title", "Reset password");
            input.put("token", resetPassToken);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, input);
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            helper.addInline("logo", image);
            helper.addInline("facebook", facebook);
            helper.addInline("twitter", twitter);
            mailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
