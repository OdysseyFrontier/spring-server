package com.enjoyTrip.OdysseyFrontiers.OpenAIService.model.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
//OpenAIService.java
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

 @Value("${api-key}")
 private String apiKey;

 public String getChatGPTResponse(String userMessage) {
     HttpClient client = HttpClient.newHttpClient();
     String requestBody = String.format(
         "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"max_tokens\":100}",
         userMessage
     );

     HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create("https://api.openai.com/v1/chat/completions"))
         .header("Content-Type", "application/json")
         .header("Authorization", "Bearer " + apiKey)
         .POST(HttpRequest.BodyPublishers.ofString(requestBody))
         .build();

     HttpResponse<String> response;
     try {
         response = client.send(request, HttpResponse.BodyHandlers.ofString());
         return response.body();
     } catch (IOException | InterruptedException e) {
         e.printStackTrace();
         return "Error occurred while getting response from OpenAI.";
     }
 }
}
