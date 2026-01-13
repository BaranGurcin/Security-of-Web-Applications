package com.BG.sw.lab8.lib.controller;

import java.util.List;

import com.BG.sw.lab8.lib.BaseController;
import com.BG.sw.lab8.lib.dto.Account;
import com.BG.sw.lab8.lib.http.HttpRequest;
import com.BG.sw.lab8.lib.http.HttpResponse;
import com.BG.sw.lab8.lib.service.AccountService;

import tools.jackson.databind.ObjectMapper;

public class AccountController extends BaseController {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AccountService accountService = new AccountService();

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        List<Account> accounts = accountService.getAllAccounts();
        String jsonAccounts = objectMapper.writeValueAsString(accounts);

        response.setStatus(200, "OK");
        response.setHeader("Content-Type", "application/json");

        response.getWriter().println(jsonAccounts);
    }
    
}
