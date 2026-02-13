package com.careerpathplanner.dto;

public class AuthResponse {

    private Long id;
    private String fullName;
    private String email;
    private String interestedArea;

    public AuthResponse() {
    }

    public AuthResponse(Long id, String fullName, String email, String interestedArea) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.interestedArea = interestedArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterestedArea() {
        return interestedArea;
    }

    public void setInterestedArea(String interestedArea) {
        this.interestedArea = interestedArea;
    }
}
