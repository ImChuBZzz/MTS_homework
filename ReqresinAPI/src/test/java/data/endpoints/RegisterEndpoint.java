package data.endpoints;

public enum RegisterEndpoint {
    REGISTER("api/register");

    private final String url;

    RegisterEndpoint(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
