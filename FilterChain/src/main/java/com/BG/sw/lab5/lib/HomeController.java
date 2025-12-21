package com.BG.sw.lab5.lib;

import com.BG.sw.lab5.lib.http.HttpRequest;
import com.BG.sw.lab5.lib.http.HttpResponse;
import com.BG.sw.lab5.lib.security.Authentication;
import com.BG.sw.lab5.lib.security.SecurityContext;

public class HomeController extends BaseController {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        String username = SecurityContext.getAuthentication()
                .map(Authentication::username)
                .orElse("Anonymous");

        response.setStatus(200, "OK");
        response.getWriter().println("""
            <html>
            <head><title>Home</title></head>
            <body>
            <h1>Welcome, %s!</h1>
            <p>This is the home page.</p>
            </body>
            </html>
            """.formatted(username));
    }
}