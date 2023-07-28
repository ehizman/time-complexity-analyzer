package com.ehizman.ftcm_analyzer_app.controller;

import com.ehizman.ftcm_analyzer_app.model.Prompt;
import com.ehizman.ftcm_analyzer_app.model.ResponseModel;
import com.ehizman.ftcm_analyzer_app.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/calc")
    public ResponseEntity<?> addPrompt(@RequestBody Prompt prompt) throws IOException {
        ResponseModel response = appService.calculateTimeComplexity(prompt);
        if (response.isSuccessful()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
