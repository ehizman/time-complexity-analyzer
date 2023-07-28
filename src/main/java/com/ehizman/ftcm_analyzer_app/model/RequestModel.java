package com.ehizman.ftcm_analyzer_app.model;

import com.google.gson.annotations.SerializedName;

public class RequestModel {
    private final RoleAndContent[] messages;

    private String model = "gpt-3.5-turbo";
    private final int temperature = 0;
    @SerializedName("max_tokens")
    private final int maxTokens = 256;

    @SerializedName("top_p")
    private final double topP = 1.0;

    @SerializedName("frequency_penalty")
    private final double frequencyPenalty = 0.0;

    @SerializedName("presence_penalty")
    private final double presencePenalty = 0.0;


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
