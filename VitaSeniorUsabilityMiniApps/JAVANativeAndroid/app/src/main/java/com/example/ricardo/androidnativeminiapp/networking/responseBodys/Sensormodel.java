
package com.example.ricardo.androidnativeminiapp.networking.responseBodys;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "transducer",
    "measure",
    "unit",
    "tag",
    "min_acceptable",
    "max_acceptable",
    "min_possible",
    "max_possible",
    "to_read"
})
public class Sensormodel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("transducer")
    private String transducer;
    @JsonProperty("measure")
    private String measure;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("min_acceptable")
    private String minAcceptable;
    @JsonProperty("max_acceptable")
    private String maxAcceptable;
    @JsonProperty("min_possible")
    private String minPossible;
    @JsonProperty("max_possible")
    private String maxPossible;
    @JsonProperty("to_read")
    private String toRead;
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

    @JsonProperty("transducer")
    public String getTransducer() {
        return transducer;
    }

    @JsonProperty("transducer")
    public void setTransducer(String transducer) {
        this.transducer = transducer;
    }

    @JsonProperty("measure")
    public String getMeasure() {
        return measure;
    }

    @JsonProperty("measure")
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    @JsonProperty("min_acceptable")
    public String getMinAcceptable() {
        return minAcceptable;
    }

    @JsonProperty("min_acceptable")
    public void setMinAcceptable(String minAcceptable) {
        this.minAcceptable = minAcceptable;
    }

    @JsonProperty("max_acceptable")
    public String getMaxAcceptable() {
        return maxAcceptable;
    }

    @JsonProperty("max_acceptable")
    public void setMaxAcceptable(String maxAcceptable) {
        this.maxAcceptable = maxAcceptable;
    }

    @JsonProperty("min_possible")
    public String getMinPossible() {
        return minPossible;
    }

    @JsonProperty("min_possible")
    public void setMinPossible(String minPossible) {
        this.minPossible = minPossible;
    }

    @JsonProperty("max_possible")
    public String getMaxPossible() {
        return maxPossible;
    }

    @JsonProperty("max_possible")
    public void setMaxPossible(String maxPossible) {
        this.maxPossible = maxPossible;
    }

    @JsonProperty("to_read")
    public String getToRead() {
        return toRead;
    }

    @JsonProperty("to_read")
    public void setToRead(String toRead) {
        this.toRead = toRead;
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
