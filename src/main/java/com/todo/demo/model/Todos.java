package com.todo.demo.model;

import java.io.Serializable;
import java.util.List;

public class Todos implements Serializable {
    private String id;
    private String address;
    private List<Todo> todos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
