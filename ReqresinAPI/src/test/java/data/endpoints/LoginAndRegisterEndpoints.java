package data.endpoints;

public enum LoginAndRegisterEndpoints {
    LOGIN("api/login"),
    REGISTER("api/register");

    private final String url;

    LoginAndRegisterEndpoints(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
