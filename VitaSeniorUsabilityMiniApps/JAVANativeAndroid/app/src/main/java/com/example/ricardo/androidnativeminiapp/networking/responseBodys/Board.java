
package com.example.ricardo.androidnativeminiapp.networking.responseBodys;

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
        "description",
        "mac_addr",
        "updated_at",
        "active",
        "node_id",
        "Boardmodel",
        "Sensors"
})
public class Board {

    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("mac_addr")
    private String macAddr;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("Boardmodel")
    private Boardmodel boardmodel;
    @JsonProperty("Sensors")
    private List<Sensor> sensors = null;
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("mac_addr")
    public String getMacAddr() {
        return macAddr;
    }

    @JsonProperty("mac_addr")
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}