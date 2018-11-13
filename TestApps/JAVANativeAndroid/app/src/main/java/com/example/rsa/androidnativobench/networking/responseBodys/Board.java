
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
    "mac_addr",
    "Boardmodel",
    "Sensors",
    "since"
})
public class Board {

    @JsonProperty("id")
    private String id;
    @JsonProperty("mac_addr")
    private String macAddr;
    @JsonProperty("Boardmodel")
    private Boardmodel boardmodel;
    @JsonProperty("Sensors")
    private List<Sensor> sensors = null;
    @JsonProperty("since")
    private String since;
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

    @JsonProperty("mac_addr")
    public String getMacAddr() {
        return macAddr;
    }

    @JsonProperty("mac_addr")
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @JsonProperty("Boardmodel")
    public Boardmodel getBoardmodel() {
        return boardmodel;
    }

    @JsonProperty("Boardmodel")
    public void setBoardmodel(Boardmodel boardmodel) {
        this.boardmodel = boardmodel;
    }

    @JsonProperty("Sensors")
    public List<Sensor> getSensors() {
        return sensors;
    }

    @JsonProperty("Sensors")
    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @JsonProperty("since")
    public String getSince() {
        return since;
    }

    @JsonProperty("since")
    public void setSince(String since) {
        this.since = since;
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
