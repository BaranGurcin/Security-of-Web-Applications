package com.vizja.lab2.controller;

import com.vizja.lab2.lib.BaseController;
import com.vizja.lab2.lib.http.HttpRequest;
import com.vizja.lab2.lib.http.HttpResponse;

public class AccountController extends BaseController {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        var writer = response.getWriter();
        writer.println(
                """
                  <html>
                    <head>
                        <title>Account Page</title>
                    </head>
                    <body>
                        <h1>Account Information</h1>
                        <p>This is the account page served by the AccountController.</p>
                    </body>
                  </html>
                """
        );
    }
}
