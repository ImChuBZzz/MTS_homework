package data.endpoints;

public enum ReqresInEndpoints {
    LOGIN("api/login"),
    REGISTER("api/register"),
    UNKNOWN("api/unknown/%d"),
    UNKNOWN_LIST("api/unknown"),
    USER("api/users/%d"),
    USERS("api/users"),
    USERS_PER_PAGE("api/users?page=%d"),
    DELAY("api/users?delay=%d");

    private final String url;

    ReqresInEndpoints(String url) {
        this.url = url;
    }

    public String getURL() {
        return  url;
    }

}
