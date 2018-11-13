
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
    "id",
    "last_values",
    "last_commit",
    "Sensormodel"
})
public class Sensor {

    @JsonProperty("id")
    private String id;
    @JsonProperty("last_values")
    private Object lastValues;
    @JsonProperty("last_commit")
    private Object lastCommit;
    @JsonProperty("Sensormodel")
    private Sensormodel sensormodel;
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

    @JsonProperty("last_values")
    public Object getLastValues() {
        return lastValues;
    }

    @JsonProperty("last_values")
    public void setLastValues(Object lastValues) {
        this.lastValues = lastValues;
    }

    @JsonProperty("last_commit")
    public Object getLastCommit() {
        return lastCommit;
    }

    @JsonProperty("last_commit")
    public void setLastCommit(Object lastCommit) {
        this.lastCommit = lastCommit;
    }

    @JsonProperty("Sensormodel")
    public Sensormodel getSensormodel() {
        return sensormodel;
    }

    @JsonProperty("Sensormodel")
    public void setSensormodel(Sensormodel sensormodel) {
        this.sensormodel = sensormodel;
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
