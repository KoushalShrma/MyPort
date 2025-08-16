package com.koushal.portfolio.controller;

import com.koushal.portfolio.dto.ChatRequest;
import com.koushal.portfolio.dto.ChatResponse;
import com.koushal.portfolio.service.GroqService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private GroqService groqService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        try {
            String response = groqService.getChatResponse(request.getMessage());
            return ResponseEntity.ok(new ChatResponse(response, true));
        } catch (Exception e) {
            return ResponseEntity.ok(new ChatResponse(
                "I'm having some technical difficulties. Please try again later.", 
                false, 
                e.getMessage()
            ));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Portfolio API is running!");
    }
}