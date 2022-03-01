package data;

public class User {

    private String id;
    private String surname;
    private String name;
    private String midName;
    private String phone;
    private String email;
    private UserStatus userStatus = UserStatus.ACTIVE;

    public User(String id, String surname, String name, String midName, String phone, String email) {
        this.surname = surname;
        this.name = name;
        this.midName = midName;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }
    public User() {}

    public String getSurname() { return this.surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getMidName() { return this.midName; }
    public void setMidName(String midName) { this.midName = midName; }

    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public UserStatus getUserStatus() { return this.userStatus; }
    public void setUserStatus(UserStatus userStatus) { this.userStatus = userStatus; }

}
