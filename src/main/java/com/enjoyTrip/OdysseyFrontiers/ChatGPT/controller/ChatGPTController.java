package com.enjoyTrip.OdysseyFrontiers.ChatGPT.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoyTrip.OdysseyFrontiers.OpenAIService.model.service.OpenAIService;

@RestController
@RequestMapping("/api/chat")
public class ChatGPTController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/message")
    public ResponseEntity<String> getChatGPTResponse(@RequestBody Map<String, String> request) {
        try {
            String userMessage = request.get("message");
            String response = openAIService.getChatGPTResponse(userMessage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing the response");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
    }
}
