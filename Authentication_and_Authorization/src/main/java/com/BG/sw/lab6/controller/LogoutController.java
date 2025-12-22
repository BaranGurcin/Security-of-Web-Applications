package com.BG.sw.lab6.controller;

import java.time.ZonedDateTime;

import com.BG.sw.lab6.lib.BaseController;
import com.BG.sw.lab6.lib.http.Cookie;
import com.BG.sw.lab6.lib.http.HttpRequest;
import com.BG.sw.lab6.lib.http.HttpResponse;
import com.BG.sw.lab6.lib.http.SessionManager;

public class LogoutController extends BaseController {

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        request.getCookie("SESSION_ID")
               .flatMap(cookie -> SessionManager.getSession(cookie.getValue()))
               .ifPresent(session -> SessionManager.removeSession(session.getId()));

        final var expiredCookie = new Cookie("SESSION_ID", "")
                .setPath("/")
                .setHttpOnly(true)
                .setExpires(ZonedDateTime.now().minusDays(1));

        response.addCookie(expiredCookie);
        response.setStatus(302, "Found");
        response.setHeader("Location", "/login");
    }
}