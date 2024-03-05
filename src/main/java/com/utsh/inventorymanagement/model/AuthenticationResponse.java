package com.utsh.inventorymanagement.model;

import java.util.List;

public class AuthenticationResponse {

    private String id;

    private String fullName;

    private String email;

    private List<String> roles;

    private String token;
    private String message;

    public AuthenticationResponse(String jwt, String message) {
        this.token = jwt;
        this.message = message;
    }


    public AuthenticationResponse(String id, String fullName, String email, List<String> roles, String token, String message) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
        this.token = token;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
