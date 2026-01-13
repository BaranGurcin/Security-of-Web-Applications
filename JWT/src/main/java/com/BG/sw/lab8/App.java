package com.BG.sw.lab8;

import java.util.List;

import com.BG.sw.lab8.lib.FrontController;
import com.BG.sw.lab8.lib.Server;
import com.BG.sw.lab8.lib.controller.LoginController;
import com.BG.sw.lab8.lib.filter.FilterChain;
import com.BG.sw.lab8.lib.filter.JwtFilter;

public class App {
    public static void main(String[] args) {
        try (var server = new Server()) {

            
            FrontController.addRoute("/login", new LoginController());


            FrontController.registerFilterChain(
                FilterChain.builder()
                    .addFilter(new JwtFilter(List.of("/api/accounts")))
                    .build()
            );

            server.start(8080);
        } 
        
    }
}
