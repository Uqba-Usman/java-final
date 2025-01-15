package com.example.demo.model;


import lombok.Data;

@Data
public class LLMRequest {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
