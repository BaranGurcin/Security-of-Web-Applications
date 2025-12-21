package com.vizja.lab2.controller;

import com.vizja.lab2.lib.BaseController;
import com.vizja.lab2.lib.http.HttpRequest;
import com.vizja.lab2.lib.http.HttpResponse;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class DashboardController extends BaseController {

    public static final String TASK_MANAGER_HTML = "src/main/resources/task-manager.html";

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            var path = Path.of(TASK_MANAGER_HTML);
            var lines = Files.readAllLines(path);

            response.setStatus(200, "OK");

            response.setHeader("Content-Type", "text/html; charset=UTF-8");

            PrintWriter writer = response.getWriter();

            for (String line : lines) {
                writer.println(line);
            }
        } catch (Exception e) {
            response.setStatus(500, "Internal Server Error");
            response.getWriter().println("Server error: " + e.getMessage());
        }
    }


}
