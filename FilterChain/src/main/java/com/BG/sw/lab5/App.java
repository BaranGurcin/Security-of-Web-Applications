package com.BG.sw.lab5;

import com.BG.sw.lab5.lib.FrontController;
import com.BG.sw.lab5.lib.HomeController;
import com.BG.sw.lab5.lib.Server;
import com.BG.sw.lab5.lib.filter.Auth;
import com.BG.sw.lab5.lib.filter.FilterChain;
import com.BG.sw.lab5.lib.filter.Logging;

public class App {
    public static void main(String[] args) {
        try (var server = new Server()) {

            FrontController.addRoute("/", new HomeController());

            FrontController.registerFilterChain(
                FilterChain.builder()
                .addFilter(new Logging())
                .addFilter(new Auth())
                .build()
            );

            server.start(8080);
        }
    }
}