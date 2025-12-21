package com.BG.sw.lab6.controller;

import static com.BG.sw.lab6.lib.http.HttpUtil.HEADER_CONTENT_TYPE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

import com.BG.sw.lab6.lib.BaseController;
import com.BG.sw.lab6.lib.http.HttpRequest;
import com.BG.sw.lab6.lib.http.HttpResponse;
import com.BG.sw.lab6.lib.security.*;
import com.BG.sw.lab6.model.*;
import com.BG.sw.lab6.repository.UserRepository;
import com.BG.sw.lab6.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistrationContorller  extends BaseController {
    private final MailService mailservice = new MailService();

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.setStatus(200, "OK");
            response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");

            Path path = Path.of("src/main/resources/static/registration.html");
            var writer = response.getWriter();

            try (var bufferedReader = Files.newBufferedReader(path)) {
                bufferedReader.transferTo(writer);
            }
        } catch (Exception exception) {
            response.setStatus(500, "Internal Server Error");
            response.getWriter().println("Server error: " + exception.getMessage());
        }
    }

    
    public void doPost(HttpRequest request, HttpResponse response) {
        String email = request.getParam("username").orElse("");
        String password = request.getParam("password").orElse("");

        final String activationCode = mailservice.generateActivationCode();
        final var token = new Token(activationCode, Instant.now().plusSeconds(15 * 60));

        final String HashPassword = SecurityUtil.hashPassword(password);
        final var user = new User(email, HashPassword, false, Role.USER, token);
        UserRepository.registerUser(user);

        mailservice.sendActivationEmail(email, activationCode);

        response.setStatus(302, "Found");
        response.setHeader("Location", "/activation?username=" + email);
    }
}
