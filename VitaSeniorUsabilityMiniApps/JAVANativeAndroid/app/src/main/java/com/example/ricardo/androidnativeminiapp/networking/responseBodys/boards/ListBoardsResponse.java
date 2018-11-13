
package com.example.ricardo.androidnativeminiapp.networking.responseBodys.boards;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ricardo.androidnativeminiapp.networking.responseBodys.Board;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "boards"
})
public class ListBoardsResponse {

    @JsonProperty("boards")
    private List<Board> boards = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("boards")
    public List<Board> getBoards() {
        return boards;
    }

    @JsonProperty("boards")
    public void setBoards(List<Board> boards) {
        this.boards = boards;
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
