package data.endpoints;

public enum LoginEndpoint {
    LOGIN("api/login");

    private final String url;

    LoginEndpoint(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
