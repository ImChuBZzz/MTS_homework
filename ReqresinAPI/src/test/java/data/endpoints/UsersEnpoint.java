package data.endpoints;

public enum UsersEnpoint {
    USER("api/users/%d"),
    USERS("api/users"),
    USERS_PER_PAGE("api/users?page=%d"),
    DELAY("api/users?delay=%d");

    private final String url;

    UsersEnpoint(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }
}
