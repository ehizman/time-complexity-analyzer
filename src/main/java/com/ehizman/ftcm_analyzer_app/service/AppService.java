package com.ehizman.ftcm_analyzer_app.service;

import com.ehizman.ftcm_analyzer_app.model.Prompt;
import com.ehizman.ftcm_analyzer_app.model.ResponseModel;

import java.io.IOException;

public interface AppService {
    ResponseModel calculateTimeComplexity(Prompt prompt) throws IOException;
}
