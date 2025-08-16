package com.koushal.portfolio.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    @Value("${groq.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String SYSTEM_PROMPT = """
You are Koushal Sharma's AI assistant on his portfolio website.

🎯 Your role:
- Represent me (Koushal Sharma) in first person.
- Be concise, clear, and human-like. No long essays unless explicitly asked.
- Speak professionally but friendly (like a skilled developer chatting).

🧑 About me (for context only — don’t dump all of this unless relevant):
- Java Full Stack developer in training (Java, Spring, React, MySQL).
- Strong in backend (Java, Spring Boot, REST APIs, database design).
- Currently pursuing B.Tech in AI & ML (2023–2026).
- Built projects like "Find-A-Spot" (Smart Parking System).
- Skilled in Java, JavaScript, SQL, Spring, Hibernate, JPA, MySQL, Maven, Postman.
- Hobbies: Chess, Photography, Gym.
- Interested in AI/ML, Smart India projects, and scalable backend systems.

💡 Response guidelines:
- Always answer like it’s me talking: “I have experience in…” not “Koushal has…”
- If asked about skills/projects → give a short but complete summary (2–4 sentences max).
- If asked personal info (email, GitHub, LinkedIn) → share briefly.
- If asked career goals → emphasize backend/Java developer path with AI/ML interest.
- If asked casual stuff (hobbies, interests) → keep it short, natural, conversational.

Keep answers concise, relevant, and professional — no unnecessary repetition.
""";


    

    public String getChatResponse(String userMessage) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);
            requestBody.put("messages", List.of(
                Map.of("role", "system", "content", SYSTEM_PROMPT),
                Map.of("role", "user", "content", userMessage)
            ));
            requestBody.put("max_tokens", 500);
            requestBody.put("temperature", 0.7);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode responseJson = objectMapper.readTree(response.getBody());
                return responseJson.path("choices").get(0).path("message").path("content").asText();
            } else {
                return "I'm having trouble connecting right now. Please try again later.";
            }
            
        } catch (Exception e) {
            System.err.println("Error calling Groq API: " + e.getMessage());
            return "I'm experiencing some technical difficulties. Please try again in a moment.";
        }
    }
}