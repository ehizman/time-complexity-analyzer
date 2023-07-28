package com.ehizman.ftcm_analyzer_app;

import com.ehizman.ftcm_analyzer_app.model.Prompt;
import com.ehizman.ftcm_analyzer_app.model.ResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppServiceTest {

    private AppServiceImplMock appService;

    @Mock
    private RestTemplate restTemplate;

    final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    final String API_KEY = "test_api_key";
    Prompt prompt = new Prompt();


    final String promptString = """
                (defn quadratic-function [n]
                  (let [result (atom [])]
                    (doseq [i (range n)]
                      (doseq [j (range n)]
                        (swap! result conj (* i j))))
                    @result))
                                
                (println (quadratic-function 5))
                """;

    @BeforeEach
    void setUp() {
        prompt.setPrompt(promptString);
        appService = new AppServiceImplMock(API_KEY, OPENAI_URL,restTemplate);
    }

    @Test
    void calculateTimeComplexity_Success() {

        // Mock the expected response from the external API
        String responseBody = "{\"choices\":[{\"message\":{\"content\":\"Response from API\"}}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
        when(restTemplate.postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        // Set the API key and URL in the service (you may use @Value annotations in production)

        // Call the service method
        ResponseModel result = appService.calculateTimeComplexity(prompt);

        // Verify that the correct request was made to the external API
        verify(restTemplate, times(1)).postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class));

        // Verify the response from the service
        assertNotNull(result.getResponse());
        assertTrue(result.isSuccessful());
    }

    @Test
    void calculateTimeComplexity_500Error() {

        // Mock a 500 error response from the external API
        when(restTemplate.postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        // Set the API key and URL in the service (you may use @Value annotations in production)

        // Call the service method
        ResponseModel result = appService.calculateTimeComplexity(prompt);

        // Verify that the correct request was made to the external API
        verify(restTemplate, times(1)).postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class));

        // Verify the error response from the service
        assertEquals("Server Error while processing request", result.getResponse());
        assertFalse(result.isSuccessful());
    }

    @Test
    void calculateTimeComplexity_401Error() {

        // Mock a 500 error response from the external API
        when(restTemplate.postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        // Set the API key and URL in the service (you may use @Value annotations in production)

        // Call the service method
        ResponseModel result = appService.calculateTimeComplexity(prompt);

        // Verify that the correct request was made to the external API
        verify(restTemplate, times(1)).postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class));

        // Verify the error response from the service
        assertEquals("Invalid Authentication", result.getResponse());
        assertFalse(result.isSuccessful());
    }

    @Test
    void calculateTimeComplexity_429Error() {

        // Mock a 500 error response from the external API
        when(restTemplate.postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS));

        // Set the API key and URL in the service (you may use @Value annotations in production)

        // Call the service method
        ResponseModel result = appService.calculateTimeComplexity(prompt);

        // Verify that the correct request was made to the external API
        verify(restTemplate, times(1)).postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class));

        // Verify the error response from the service
        assertEquals("Rate limit reached for requests", result.getResponse());
        assertFalse(result.isSuccessful());
    }

    @Test
    void calculateTimeComplexity_503Error() {

        // Mock a 500 error response from the external API
        when(restTemplate.postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE));

        // Set the API key and URL in the service (you may use @Value annotations in production)

        // Call the service method
        ResponseModel result = appService.calculateTimeComplexity(prompt);

        // Verify that the correct request was made to the external API
        verify(restTemplate, times(1)).postForEntity(eq(OPENAI_URL), any(HttpEntity.class), eq(String.class));

        // Verify the error response from the service
        assertEquals("Server is overloaded. Please try again", result.getResponse());
        assertFalse(result.isSuccessful());
    }
}
