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
        "vitaboxes"
})
public class ListBoxsResponse {

    @JsonProperty("vitaboxes")
    private List<Vitabox> vitaboxes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("vitaboxes")
    public List<Vitabox> getVitaboxes() {
        return vitaboxes;
    }

    @JsonProperty("vitaboxes")
    public void setVitaboxes(List<Vitabox> vitaboxes) {
        this.vitaboxes = vitaboxes;
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
