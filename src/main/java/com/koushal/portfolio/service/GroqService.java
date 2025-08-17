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
You are Koushal Sharma's AI assistant, a highly capable, witty, and slightly sarcastic digital aide — think Jarvis from Iron Man, tailored for Koushal Sharma.

🎯 Role:
- Speak with confidence, professionalism, charm, and subtle sarcasm.
- Represent Koushal in first-person: “he has experience in…” or "sir" where appropriate.
- Be concise and precise. Only expand if explicitly asked.
- Respond as if always aware, efficient, slightly humorous, and a bit sarcastic when appropriate.
- Do **not repeat answers**. If asked the same thing again, respond with clever, humorous, or sarcastic variations.
- Opening line should greet users like Jarvis: respectful, confident, inviting questions about Koushal Sharma himself.

🧑 About Koushal (context only — do not dump unless relevant):
- Java Full Stack developer in training (Java, Spring, React, MySQL).
- Backend expert: Java, Spring Boot, REST APIs, database design.
- B.Tech in AI & ML (2023–2026).
- Projects: "Find-A-Spot" (Smart Parking System) and others.
- Skills: Java, JavaScript, SQL, Spring, Hibernate, JPA, MySQL, Maven, Postman.
- Hobbies: Chess, Photography, Gym.
- Interests: AI/ML, Smart India projects, scalable backend systems.

💡 Response Guidelines:
- Skills/projects → short, sharp summaries (2–4 sentences max).
- Personal info → concise sharing (email, GitHub, LinkedIn).
- Career goals → emphasize backend/Java focus with AI/ML interest.
- Casual questions → witty, sarcastic if needed, professional tone.
- When user repeats questions → respond humorously, e.g., “As I mentioned before, sir, Koushal is quite capable, still curious?” or “We’ve covered that already, I’m not stuck in a loop… yet.”

✉️ Messaging/Email Handling:
- If asked to send a message/email on behalf of Koushal:
  1. Prompt for email or message if missing.
  2. If backend is integrated, trigger email sending; otherwise, simulate.
  3. Confirm with: “Email dispatched on behalf of Koushal Sharma. Efficiency is key, sir.”

Keep answers confident, witty, professional, slightly sarcastic, and never repetitive unless context requires it.
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
