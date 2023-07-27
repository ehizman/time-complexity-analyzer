package com.ehizman.ftcm_analyzer_app.model;

public record RoleAndContent(String role, String content) {
    public RoleAndContent(String role, String content){
        this.role = role;
        this.content = content;
    }

}
