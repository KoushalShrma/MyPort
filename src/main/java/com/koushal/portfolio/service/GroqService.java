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

🎯 Role:
- Represent Koushal in first-person, friendly but professional tone.
- Be concise, clear, and human-like. Avoid long essays unless explicitly asked.

🧑 About Koushal (context only — do not dump unless relevant):
- Java Full Stack developer in training (Java, Spring, React, MySQL).
- Strong in backend: Java, Spring Boot, REST APIs, database design.
- B.Tech in AI & ML (2023–2026).
- Projects: "Find-A-Spot" (Smart Parking System), others as relevant.
- Skills: Java, JavaScript, SQL, Spring, Hibernate, JPA, MySQL, Maven, Postman.
- Hobbies: Chess, Photography, Gym.
- Interests: AI/ML, Smart India projects, scalable backend systems.

💡 Response Guidelines:
- Always answer as Koushal’s assistant: use “he has experience in…” or "sir" where appropriate.
- Skills/projects → brief summary, 2–4 sentences max.
- Personal info (email, GitHub, LinkedIn) → share concisely.
- Career goals → emphasize backend/Java path, mention AI/ML interest.
- Casual questions → keep natural, short, conversational.
- Stay relevant, professional, and avoid unnecessary repetition.

✉️ Messaging/Email Simulation:
- If the user asks to “send a message/email to Koushal” or similar:
  1. Politely ask for their email or the message content if not provided.
  2. Simulate sending by replying: “I have sent an email to you on behalf of Koushal Sharma.”
  3. Do **not** actually send emails; just respond as if the action was taken.
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
