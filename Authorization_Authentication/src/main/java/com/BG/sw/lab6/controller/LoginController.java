package com.BG.sw.lab6.controller;

import lombok.extern.slf4j.Slf4j;

import static com.BG.sw.lab6.lib.http.HttpUtil.HEADER_CONTENT_TYPE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.Optional;

import com.BG.sw.lab6.lib.BaseController;
import com.BG.sw.lab6.lib.http.Cookie;
import com.BG.sw.lab6.lib.http.HttpRequest;
import com.BG.sw.lab6.lib.http.HttpResponse;
import com.BG.sw.lab6.lib.http.Session;
import com.BG.sw.lab6.lib.http.SessionManager;
import com.BG.sw.lab6.lib.security.SecurityUtil;
import com.BG.sw.lab6.model.User;
import com.BG.sw.lab6.repository.UserRepository;



@Slf4j
public class LoginController extends BaseController {
    
    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.setStatus(200, "OK");
            response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");

            Path path = Path.of("src/main/resources/static/login.html");
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
        String password = request.getParam("password").orElse("");

        if (email.isBlank() || password.isBlank()) {
            sendError(response, "E-mail and password required");
            return;
        }

        Optional<User> userOpt = UserRepository.findByUsername(email);

        if (userOpt.isEmpty()) {
            sendError(response, "Invalid credentials");
            return;
        }

        User user = userOpt.get();

        if (!user.isActivated()) {
            sendError(response, "Account not activated");
            return;
        }

        boolean validPassword = SecurityUtil.verifyPassword(password, user.hashPassword());
        if (!validPassword) {
            sendError(response, "Invalid credentials");
            return;
        }

        Session session = SessionManager.createSession();
        session.setAttribute("username", user.username());
        session.setAttribute("role", user.role());

        final var cookie = new Cookie("SESSION_ID", session.getId())
            .setPath("/")
            .setHttpOnly(true)
            .setSameSite(Cookie.SameSite.Strict)
            .setExpires(ZonedDateTime.now().plusMinutes(30));

        response.addCookie(cookie);

        
        response.setStatus(302, "Found");
        response.setHeader("Location", "/todo");
    }
    
    
    private void sendError(HttpResponse response, String message) {
        response.setStatus(401, "Unauthorized");
        response.setHeader(HEADER_CONTENT_TYPE, "text/html; charset=UTF-8");
        response.getWriter().println("""
                """.formatted(message));
    }
}
