package com.BG.lab3;

import com.BG.lab3.controller.StaticController;
import com.BG.lab3.controller.TodoController;
import com.BG.lab3.lib.FrontController;
import com.BG.lab3.lib.Server;

public class App {
    public static void main(String[] args) {

        try (final var server = new Server()) {

            FrontController.addRoute("/", new StaticController());           
            FrontController.addRoute("/style.css", new StaticController());   
            FrontController.addRoute("/script.js", new StaticController());   
            FrontController.addRoute("/api/todos", new TodoController());

            server.start(8080);
        }

    }
}
