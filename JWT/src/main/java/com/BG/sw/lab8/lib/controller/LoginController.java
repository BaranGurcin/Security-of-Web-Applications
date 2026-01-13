package com.BG.sw.lab8.lib.controller;

import tools.jackson.databind.ObjectMapper;

import static com.BG.sw.lab8.lib.security.SecurityUtil.*;

import java.util.Map;
import java.util.Optional;

import com.BG.sw.lab8.lib.BaseController;
import com.BG.sw.lab8.lib.dto.LoginRequest;
import com.BG.sw.lab8.lib.dto.User;
import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;
import com.BG.sw.lab8.lib.http.HttpUtil;
import com.BG.sw.lab8.lib.service.JwtService;
import com.BG.sw.lab8.lib.service.UserService;

public class LoginController extends BaseController {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final UserService userService = new UserService();


    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        final LoginRequest loginRequest = objectMapper.readValue(request.getBody(), LoginRequest.class);
        Optional<User> user = userService.getUserByUsername(loginRequest.username());

        if (user.isEmpty()) {
            response.setStatus(400, "Bad Request");
            response.setHeader(HttpUtil.HEADER_CONTENT_TYPE, "application/json");
            response.getWriter().println(objectMapper.writeValueAsString(
                    Map.of("message", "Invalid username or password")
            ));

            response.flush();
            return;
        }
    

    if (!verifyPassword(loginRequest.password(), user.get().hashPassword())) {
        response.setStatus(400, "Bad Request");
        response.setHeader(HttpUtil.HEADER_CONTENT_TYPE, "application/json");
        response.getWriter().println(objectMapper.writeValueAsString(
                Map.of("message", "Invalid username or password")
        ));

        response.flush();
        return;
    }

    String token = JwtService.generateToken(user.get());

    response.setStatus(200, "OK");
    response.getWriter().println(objectMapper.writeValueAsString(
            Map.of("jwt-token", token)
    ));
    
}

}