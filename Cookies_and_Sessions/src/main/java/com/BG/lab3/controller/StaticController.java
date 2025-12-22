package com.BG.lab3.controller;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import com.BG.lab3.lib.BaseController;
import com.BG.lab3.lib.http.Cookie;
import com.BG.lab3.lib.http.HttpRequest;
import com.BG.lab3.lib.http.HttpResponse;

public class StaticController extends BaseController {
    private static final String TODO = "src/main/resources/static/todo.html";

@Override
public void doGet(HttpRequest request, HttpResponse response) {
    try {

        Path filePath = Path.of(TODO);
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        for (String line : Files.readAllLines(filePath)) {
            writer.println(line);
        }

    } catch (Exception e) {
        response.setStatus(500, "Server Error");
    }
}


 @Override
    public void doPost(HttpRequest request, HttpResponse response) {

        try {
            String body = request.getBody();
            String theme = body.contains("dark") ? "dark" : "light";

            Cookie cookie = new Cookie("theme", theme).setPath("/").setMaxAge(86400);
            response.addCookie(cookie);

            response.setHeader("Content-Type", "application/json; charset=UTF-8");
        } catch (Exception e) {
            response.setStatus(500, "Server Error");
        }
    }
}
