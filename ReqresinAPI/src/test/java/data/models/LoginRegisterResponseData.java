package data.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRegisterResponseData {
    private Integer id;
    private String token;

    public LoginRegisterResponseData() {}

    public LoginRegisterResponseData(String token) {
        this.token = token;
    }

    public LoginRegisterResponseData(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
