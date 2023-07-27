package com.ehizman.ftcm_analyzer_app.model;

import com.ehizman.ftcm_analyzer_app.service.AppServiceImpl;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class RequestModel {
    private final RoleAndContent[] messages;

//    @Value("${model}")
    private String model = "gpt-3.5-turbo";
//    private final String prompt;
    private final int temperature = 0;
    @SerializedName("max_tokens")
    private final int maxTokens = 256;

    @SerializedName("top_p")
    private final double topP = 1.0;

    @SerializedName("frequency_penalty")
    private final double frequencyPenalty = 0.0;

    @SerializedName("presence_penalty")
    private final double presencePenalty = 0.0;

//    private final String stop = "[\"\n\"]";

    public RequestModel (String prompt) {
        RoleAndContent userRoleAndContent = new RoleAndContent("user", prompt);
        RoleAndContent systemRoleAndContent = new RoleAndContent("system",
                "You will be provided with a code snippet, your task is to determine what language it is implemented in and to calculate its time complexity.");

        RoleAndContent[] roleAndContentArray = new RoleAndContent[2];
        roleAndContentArray[0] = systemRoleAndContent;
        roleAndContentArray[1] = userRoleAndContent;

        this.messages = roleAndContentArray;
    }
}
