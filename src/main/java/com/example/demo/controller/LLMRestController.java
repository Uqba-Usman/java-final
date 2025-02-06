package com.example.demo.controller;


import com.example.demo.model.LLMRequest;
import com.example.demo.model.LLMResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/llm")
public class LLMRestController {

//    private OllamaLLMService ollamaLLMService;

//    public LLMRestController(OllamaLLMService ollamaLLMService){
//        this.ollamaLLMService = ollamaLLMService;
//    }
//
//    @PostMapping("/chat")
//    public ResponseEntity<LLMResponse> chat(@RequestBody LLMRequest llmRequest){
//        String chatResponse = ollamaLLMService.chat(llmRequest.getQuery());
//
//        LLMResponse llmResponse = new LLMResponse("Success", chatResponse);
//
//        return ResponseEntity.ok(llmResponse);
//    }
//
    @Autowired
    OpenAiChatModel chatModel;
    @PostMapping("/skills")
    public ResponseEntity<Map<String, Object>> chat(@RequestBody LLMRequest llmRequest) {
        try {
            System.out.println("Query: " + llmRequest.getQuery());

            Prompt prompt = new Prompt("Provide a JSON response listing only the specific skill set required for "
                    + llmRequest.getQuery()
                    + ". The JSON should only include an array of skills without any additional text or code block markers. For example, if I provide 'frontend,' the response should be like: {\"skills\": [\"HTML\", \"CSS\", \"JavaScript\", \"React\", \"Vue.js\"]}. Now, list the skills for frontend");

            ChatResponse response = chatModel.call(prompt);
            String rawResponse = response.getResult().getOutput().getContent();

            System.out.println("Raw Response: " + rawResponse);


            // Clean up the response
            rawResponse = rawResponse.trim()
                    .replaceAll("^```json", "")
                    .replaceAll("^```", "")
                    .replaceAll("```$", "");

            // Parse the response
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = objectMapper.readValue(rawResponse, Map.class);

            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            System.out.println("Raw Response: " + e);

            return ResponseEntity.status(500).body(Map.of("error", "Failed to process response"));
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<Map<String, Object>> detail(@RequestBody LLMRequest llmRequest) {
        try {
            System.out.println("Query: " + llmRequest.getQuery());

            Prompt prompt = new Prompt("Provide a detailed explanation of the skill I specify. The response should outline what one needs to know about this skill to progress from a basic to a professional level. The explanation should be divided into sections for beginner, intermediate, and advanced levels, with each section listing key concepts, tools, and practices. Also gave me some reference links to study\n" +
                    "\n" +
                    "The response should be formatted as a JSON object with a single key, \"html\", where the value is a string of HTML. The HTML should be structured with headings and lists for clarity. Exclude code block markers or any unnecessary formatting, providing only the content that can be embedded in the <body> of an HTML document." + llmRequest.getQuery());

            ChatResponse response = chatModel.call(prompt);
            String rawResponse = response.getResult().getOutput().getContent();

            System.out.println("Raw Response: " + rawResponse);

            rawResponse = rawResponse.trim()
                    .replaceAll("^```json", "")
                    .replaceAll("^```", "")
                    .replaceAll("```$", "");

            // Parse the response
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = objectMapper.readValue(rawResponse, Map.class);

            return ResponseEntity.ok(jsonResponse);

        } catch (Exception e) {
            System.out.println("Raw Response: " + e);

            return ResponseEntity.status(500).body(Map.of("error", "Failed to process response"));
        }
    }
}
