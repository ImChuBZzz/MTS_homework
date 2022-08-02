package data.endpoints;

public enum UnknownEndpoints {
    UNKNOWN("api/unknown/%d"),
    UNKNOWN_LIST("api/unknown");

    private final String url;

    UnknownEndpoints(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
