package com.example.demo.controller;


import com.example.demo.model.LLMRequest;
import com.example.demo.model.LLMResponse;
import com.example.demo.service.OllamaLLMService;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/llm")
public class LLMRestController {

    private OllamaLLMService ollamaLLMService;

    public LLMRestController(OllamaLLMService ollamaLLMService){
        this.ollamaLLMService = ollamaLLMService;
    }

    @PostMapping("/chat")
    public ResponseEntity<LLMResponse> chat(@RequestBody LLMRequest llmRequest){
        String chatResponse = ollamaLLMService.chat(llmRequest.getQuery());

        LLMResponse llmResponse = new LLMResponse("Success", chatResponse);

//        return ollamaLLMService.chat(llmRequest.getQuery());
        return ResponseEntity.ok(llmResponse);
    }
}
