
package com.example.rsa.androidnativobench.networking.responseBodys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "birthdate",
    "name",
    "gender",
    "since",
    "active",
    "weight",
    "height",
    "Boards",
    "Profiles",
    "Doctors"
})
public class Patient {

    @JsonProperty("id")
    private String id;
    @JsonProperty("birthdate")
    private String birthdate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("since")
    private String since;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("weight")
    private Object weight;
    @JsonProperty("height")
    private Object height;
    @JsonProperty("Boards")
    private List<Board> boards = null;
    @JsonProperty("Profiles")
    private List<Profile> profiles = null;
    @JsonProperty("Doctors")
    private List<Doctor> doctors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("birthdate")
    public String getBirthdate() {
        return birthdate;
    }

    @JsonProperty("birthdate")
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("since")
    public String getSince() {
        return since;
    }

    @JsonProperty("since")
    public void setSince(String since) {
        this.since = since;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("weight")
    public Object getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Object weight) {
        this.weight = weight;
    }

    @JsonProperty("height")
    public Object getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Object height) {
        this.height = height;
    }

    @JsonProperty("Boards")
    public List<Board> getBoards() {
        return boards;
    }

    @JsonProperty("Boards")
    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    @JsonProperty("Profiles")
    public List<Profile> getProfiles() {
        return profiles;
    }

    @JsonProperty("Profiles")
    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @JsonProperty("Doctors")
    public List<Doctor> getDoctors() {
        return doctors;
    }

    @JsonProperty("Doctors")
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
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
