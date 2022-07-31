package data.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedUserData {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;

    public CreatedUserData() {}

    public CreatedUserData(String name) {
        this.name = name;
    }

    public CreatedUserData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public CreatedUserData(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public CreatedUserData(String name, String job, String updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public CreatedUserData(String name, String job, String id, String createdAt, String updatedAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
