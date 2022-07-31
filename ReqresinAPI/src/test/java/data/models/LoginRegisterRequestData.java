package data.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRegisterRequestData {
    @JsonProperty()
    private String email;
    @JsonProperty()
    private String password;

    public LoginRegisterRequestData() {}

    public LoginRegisterRequestData(String email) {
        this.email = email;
    }

    public LoginRegisterRequestData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
