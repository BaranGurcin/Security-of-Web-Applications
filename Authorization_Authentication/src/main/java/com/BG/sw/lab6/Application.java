package com.BG.sw.lab6;

import java.util.List;

import com.BG.sw.lab6.controller.AccountActivationController;
import com.BG.sw.lab6.controller.LoginController;
import com.BG.sw.lab6.controller.LogoutController;
import com.BG.sw.lab6.controller.RegistrationContorller;
import com.BG.sw.lab6.controller.TodoController;
import com.BG.sw.lab6.lib.FrontController;
import com.BG.sw.lab6.lib.Server;
import com.BG.sw.lab6.lib.filter.AuthFilter;
import com.BG.sw.lab6.lib.filter.FilterChain;


public class Application {
    public static void main(String[] args) {

        try (var server = new Server()) {

            FrontController.addRoute("/todo", new TodoController());
            FrontController.addRoute("/login", new LoginController());
            FrontController.addRoute("/register", new RegistrationContorller());
            FrontController.addRoute("/activation", new AccountActivationController());
            FrontController.addRoute("/logout", new LogoutController());

            FrontController.registerFilterChain(
                FilterChain.builder()
                    .addFilter(new AuthFilter(List.of("/todo")))
                    .build()
            );

            server.start(8080);
        }
    }
}
