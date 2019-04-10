package br.com.fiap.dogs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public class ListAllResponse {

    private String status;

    @JsonProperty("message")
    private HashMap<String, List<String>> breeds;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, List<String>> getBreeds() {
        return breeds;
    }

    public void setBreeds(HashMap<String, List<String>> breeds) {
        this.breeds = breeds;
    }
}
