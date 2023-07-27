package com.ehizman.ftcm_analyzer_app.service;

import com.ehizman.ftcm_analyzer_app.model.Prompt;
import com.ehizman.ftcm_analyzer_app.model.RequestModel;
import com.ehizman.ftcm_analyzer_app.model.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Service
public class AppServiceImpl implements AppService{
    @Value("${api_key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openai_url}")
    private String OPENAI_URL;

    Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);
    @Override
    public ResponseModel calculateTimeComplexity(Prompt prompt) {
        // create header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        RequestModel appRequestModel = new RequestModel(prompt.getPrompt());
        Gson gson = new Gson();
        String requestJson = gson.toJson(appRequestModel);


        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response;
        try{
            response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        } catch (HttpClientErrorException e){
            logger.info("Exception --> {}", e.getMessage());
            switch (e.getStatusCode().value()) {
                case 401 -> {
                    return new ResponseModel("Invalid Authentication", false);
                }
                case 429 -> {
                    return new ResponseModel("Rate limit reached for requests", false);
                }
                case 500 -> {
                    return new ResponseModel("Server Error while processing request", false);
                }

                case 503 -> {
                    return new ResponseModel("Server is overloaded. Please try again", false);
                }
                default -> {
                    return new ResponseModel("Undefined Error. <p> <a href=\"mailto:edemaehiz@gmailcom?subject=Error in function time complexity analyzer&body=Add message\">Please contact admin</a></p>", false);
                }
            }
        }

        // if request is successful
        if (response.getStatusCode().is2xxSuccessful()){
            // parse the body of the response using Gson's JsonParser
            JsonElement jsonElement = JsonParser.parseString(Objects.requireNonNull(response.getBody()));

            // convert the JsonElement to Java JsonNode using Jackson's Object Mapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode;
            try {
                jsonNode = objectMapper.readTree(jsonElement.toString());
            } catch (JsonProcessingException e){
                // if there is an error processing the JSON
                e.printStackTrace();
                return new ResponseModel("An error occurred while processing JSON response from ChatGPT", false);
            }
            String result = jsonNode.get("choices").get(0).get("message").get("content").asText();
            return new ResponseModel(result, true);
        }  else {
            return new ResponseModel("error making request", false);
        }
    }
}
