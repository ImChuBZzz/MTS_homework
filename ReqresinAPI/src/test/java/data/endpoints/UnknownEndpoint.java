package data.endpoints;

public enum UnknownEndpoint {
    UNKNOWN("api/unknown/%d"),
    UNKNOWN_LIST("api/unknown");

    private final String url;

    UnknownEndpoint(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
