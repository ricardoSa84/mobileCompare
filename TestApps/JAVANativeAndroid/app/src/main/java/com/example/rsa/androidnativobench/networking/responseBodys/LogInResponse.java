package com.example.rsa.androidnativobench.networking.responseBodys;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "token",
        "id",
        "name",
        "email",
        "is_admin",
        "is_doctor",
        "photo",
        "warnings",
        "errors"
})
public class LogInResponse {

    @JsonProperty("token")
    private String token;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("is_doctor")
    private Boolean isDoctor;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("warnings")
    private Integer warnings;
    @JsonProperty("errors")
    private Integer errors;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("is_admin")
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    @JsonProperty("is_admin")
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @JsonProperty("is_doctor")
    public Boolean getIsDoctor() {
        return isDoctor;
    }

    @JsonProperty("is_doctor")
    public void setIsDoctor(Boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("warnings")
    public Integer getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(Integer warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("errors")
    public Integer getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(Integer errors) {
        this.errors = errors;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
