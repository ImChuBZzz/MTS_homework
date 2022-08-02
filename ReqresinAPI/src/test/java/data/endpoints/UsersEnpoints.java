package data.endpoints;

public enum UsersEnpoints {
    USER("/api/users/%s"),
    USERS("/api/users"),
    USERS_PER_PAGE("/api/users?page=%s"),
    DELAY("/api/users?delay=%s");

    private final String url;

    UsersEnpoints(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }
}
