package com.vizja.lab2;

import com.vizja.lab2.controller.AboutController;
import com.vizja.lab2.controller.DashboardController;
import com.vizja.lab2.controller.TodoController;
import com.vizja.lab2.lib.FrontController;
import com.vizja.lab2.lib.Server;

public class App {
    public static void main(String[] args) {


        try (final var server = new Server()) {

            FrontController.addRoute("/", new DashboardController());
            FrontController.addRoute("/about", new AboutController());
            FrontController.addRoute("/api/todos", new TodoController());


            server.start(8080);
        }


    }
}


