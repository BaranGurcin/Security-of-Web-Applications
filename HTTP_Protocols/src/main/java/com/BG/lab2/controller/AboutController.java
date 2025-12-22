package com.vizja.lab2.controller;

import com.vizja.lab2.lib.BaseController;
import com.vizja.lab2.lib.http.HttpRequest;
import com.vizja.lab2.lib.http.HttpResponse;

public class AboutController extends BaseController {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        var writer = response.getWriter();
        writer.println(
                """
                  <html>
                    <head>
                        <title>About Page</title>
                    </head>
                    <body>
                        <h1>About Us</h1>
                        <p>This is the about page served by the AboutController.</p>
                    </body>
                  </html>
                """
        );
    }
}
