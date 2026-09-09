package com.mytutor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import jakarta.annotation.PostConstruct;
import java.util.*;


public class AiService{
    private final RestClient client=RestClient.create(); private final ObjectMapper mapper=new ObjectMapper();
    private final RuleBasedRefiner fallback;
    @Value("${app.ai.provider:ollama}") String provider;
    @Value("${app.ai.ollama-url:http://localhost:11434/api/chat}") String ollamaUrl;
    @Value("${app.ai.ollama-model:llama3.2:3b}") String ollamaModel;
    @Value("${app.ai.gemini-api-key:}") String geminiKey;
    @Value("${app.ai.gemini-model:gemini-2.5-flash}") String geminiModel;
    public AiService(RuleBasedRefiner fallback){this.fallback=fallback;}

}
