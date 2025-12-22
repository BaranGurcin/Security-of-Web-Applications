package com.vizja.lab2.controller;

import com.vizja.lab2.dto.Todo;
import com.vizja.lab2.lib.BaseController;
import com.vizja.lab2.lib.http.HttpRequest;
import com.vizja.lab2.lib.http.HttpResponse;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TodoController extends BaseController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public final List<Todo> todos = new ArrayList<>(
            List.of(
                    new Todo(1, "Learn Java", false),
                    new Todo(2, "Build a web app", false),
                    new Todo(3, "Deploy the app", false)
            )
    );

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setStatus(200, "OK");
        String jsonTodos = objectMapper.writeValueAsString(todos);
        response.getWriter().println(jsonTodos);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            String body = request.getBody();
            Todo newTodo = objectMapper.readValue(body, Todo.class);

            int newId = todos.size() + 1;
            Todo todoWithId = new Todo(newId, newTodo.text(), newTodo.completed());

            todos.add(todoWithId);

            response.setStatus(201, "Created");
            String jsonTodo = objectMapper.writeValueAsString(todoWithId);
            response.setHeader("Content-Type", "application/json");
            response.getWriter().println(jsonTodo);

        } catch (Exception e) {
            response.setStatus(400, "Bad Request");
            response.getWriter().println("Invalid JSON format: " + e.getMessage());
        }
    }

    @Override
    public void doPut(HttpRequest request, HttpResponse response) {
        try {
            String body = request.getBody();
            Todo updatedTodo = objectMapper.readValue(body, Todo.class);

            for(Todo todo : todos) {
                if(todo.id().equals(updatedTodo.id())) {
                    todos.remove(todo);
                    todos.add(updatedTodo);
                    response.setStatus(200, "OK");
                    String jsonTodo = objectMapper.writeValueAsString(updatedTodo);
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().println(jsonTodo);
                    return;
                }

            }

            response.setStatus(404, "Not Found");
            response.getWriter().println("Todo with ID " + updatedTodo.id() + " not found.");
        } catch (Exception e) {
            response.setStatus(400, "Bad Request");
            response.getWriter().println("Invalid JSON format: " + e.getMessage());
        }
    }

    @Override
    public void doDelete(HttpRequest request, HttpResponse response) {
        try {
            String body = request.getBody();
            Todo todoToDelete = objectMapper.readValue(body, Todo.class);

            for(Todo todo : todos) {
                if(todo.id().equals(todoToDelete.id())) {
                    todos.remove(todo);
                    response.setStatus(200, "OK");
                    response.getWriter().println("Todo with ID " + todoToDelete.id() + " deleted.");
                    return;
                }

            }

            response.setStatus(404, "Not Found");
            response.getWriter().println("Todo with ID " + todoToDelete.id() + " not found.");
        } catch (Exception e) {
            response.setStatus(400, "Bad Request");
            response.getWriter().println("Invalid JSON format: " + e.getMessage());
        }
    }

}
