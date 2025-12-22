package com.BG.sw.lab6.controller;

import static com.BG.sw.lab6.lib.http.HttpUtil.HEADER_CONTENT_TYPE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Optional;

import com.BG.sw.lab6.lib.BaseController;
import com.BG.sw.lab6.lib.http.HttpRequest;
import com.BG.sw.lab6.lib.http.HttpResponse;
import com.BG.sw.lab6.model.User;
import com.BG.sw.lab6.repository.UserRepository;

public class AccountActivationController extends BaseController {
    
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.setStatus(200, "OK");
            response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");
            
            Path path = Path.of("src/main/resources/static/activation.html");
            var writer = response.getWriter();

            try (var bufferedReader = Files.newBufferedReader(path)) {
                bufferedReader.transferTo(writer);
            }
        } catch (Exception exception) {
            response.setStatus(500, "Internal Server Error");
            response.getWriter().println("Server error: " + exception.getMessage());
        }
    }
    
    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        String email = request.getParam("username").orElse("");
        String code = request.getParam("code").orElse("");

        if (email.isBlank()|| code.isBlank()) {
            sendError(response, "Missing parameters");
            return;
        }  

        Optional<User> userOpt = UserRepository.findByUsername(email);

        if (userOpt.isEmpty()) {
            sendError(response, "User not found");
            return;
        }

        User user = userOpt.get();
        var token = user.activationToken();

        if (token == null) {
            sendError(response, "Invalid activation code");
            return;
        }

        if (!token.value().equals(code)) {
            sendError(response, "Invalid activation code");
            return;
        }

        if (token.expiryTimestamp().isBefore(Instant.now())) {
            sendError(response, "Activation code expired");
            return;
        }

        var updatedUser = new User( user.username(), user.hashPassword(), true, user.role(), null);
        UserRepository.update(updatedUser);

        response.setStatus(302, "Found");
        response.setHeader("Location", "/login?activated=true");
    }

    private void sendError(HttpResponse response, String message) {
        response.setStatus(400, "Bad Request");
        response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");
        response.getWriter().println("<h3 style='color:red; text-align:center;'>" + message + "</h3>");
    }
}
