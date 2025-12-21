package com.BG.lab3.controller;

import tools.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import com.BG.lab3.lib.BaseController;
import com.BG.lab3.lib.http.HttpRequest;
import com.BG.lab3.lib.http.HttpResponse;
import com.BG.lab3.model.Todo;


public class TodoController extends BaseController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static List<Todo> todos = new ArrayList<>();
    private static long nextId = 1;

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {

        String savedtodos = request.getCookie("todos").map(c -> c.getValue()).orElse("[]");
        response.getWriter().println(savedtodos);

        String jsonTodos = objectMapper.writeValueAsString(todos);
        response.getWriter().println(jsonTodos);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            String body = request.getBody();
            Todo addTodo = objectMapper.readValue(body, Todo.class);

            response.addCookie(new com.BG.lab3.lib.http.Cookie("todos", body));

            addTodo.setId(nextId);
            todos.add(addTodo);

            String json = objectMapper.writeValueAsString(addTodo);
            response.getWriter().println(json);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Server Error");
        }
    }

    @Override
    public void doPut(HttpRequest request, HttpResponse response) {
        try {
        String body = request.getBody();
        Todo updatedTodo = objectMapper.readValue(body, Todo.class);

        for (Todo todo : todos) {
            if (todo.getId() == updatedTodo.getId()) {
                todo.setText(updatedTodo.getText());
                todo.setCompleted(updatedTodo.isCompleted());
                response.getWriter().println(objectMapper.writeValueAsString(todo));
                return;
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpRequest request, HttpResponse response) {
        try {
            String body = request.getBody();
            Todo todoToDelete = objectMapper.readValue(body, Todo.class);

            boolean removed = todos.removeIf(todo -> todo.getId() == todoToDelete.getId());

            if (removed) {
                response.getWriter().println("{\"message\":\"Todo deleted successfully\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("{\"error\":\"Something went wrong\"}");
        }
    }

}