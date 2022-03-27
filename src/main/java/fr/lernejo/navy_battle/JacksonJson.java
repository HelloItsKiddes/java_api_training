package fr.lernejo.navy_battle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class JacksonJson {
    public final String id;
    public final String message;
    public final String url;

    public JacksonJson(@JsonProperty("id") JsonNode id, @JsonProperty("message") JsonNode message, @JsonProperty("url") JsonNode url) {
        this.id = id.toString();
        this.message = message.toString();
        this.url = url.toString();
    }

}
