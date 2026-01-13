package com.BG.sw.lab8;

import java.util.List;

import com.BG.sw.lab8.lib.FrontController;
import com.BG.sw.lab8.lib.Server;
import com.BG.sw.lab8.lib.controller.ApiKeyController;
import com.BG.sw.lab8.lib.filter.ApiFilter;
import com.BG.sw.lab8.lib.filter.FilterChain;

public class App {
    public static void main(String[] args) {
        try (var server = new Server()) {

            
            FrontController.addRoute("/api/accounts", new ApiKeyController());
            FrontController.addRoute("/api-keys", new ApiKeyController());

            FrontController.registerFilterChain(
                FilterChain.builder()
                    .addFilter(new ApiFilter(List.of("/api/accounts")))
                    .build()
            );

            server.start(8080);
        } 
        
    }
}
