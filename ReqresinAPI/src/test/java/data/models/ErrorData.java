package data.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {
    @JsonProperty()
    private String error;

    public ErrorData() {}

    public ErrorData(String message) {
        this.error = message;
    }

    public String getMessage() {
        return error;
    }
}
