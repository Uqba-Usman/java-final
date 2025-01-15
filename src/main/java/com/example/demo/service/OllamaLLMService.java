package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service@Slf4j
public class OllamaLLMService {

    private OllamaChatModel chatModel;

    OllamaLLMService(OllamaChatModel chatModel){
        this.chatModel = chatModel;
    }

    public String chat(String query){
        String response = chatModel.call(query);
        return response;
    }

}
