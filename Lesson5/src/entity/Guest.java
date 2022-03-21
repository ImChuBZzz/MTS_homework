package entity;

import java.util.Objects;

public class Guest {
    private String phone;
    private String email;
    private String lastName;
    private String firstName;
    private String midName;

    public Guest() {}
    public Guest(String phone, String email, String lastName, String firstName, String midName) {
        this.phone = phone;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.midName = midName;
    }

    public String getPhone() { return this.phone; }
    public String getEmail() { return this.email; }
    public String getLastName() { return this.lastName; }
    public String getFirstName() { return this.firstName; }
    public String getMidName() { return this.midName; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMidName(String midName) { this.midName = midName; }

    @Override
    public String toString() {
        return "Guest: {" +
                this.lastName + " " +
                this.firstName + " " +
                this.midName + ". " +
                "Phone: " + this.phone + ", " +
                "email: " + this.email + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(this.phone, guest.phone)
                && Objects.equals(this.email, guest.email)
                && Objects.equals(this.lastName, guest.lastName)
                && Objects.equals(this.firstName, guest.firstName)
                && Objects.equals(this.midName, guest.midName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.phone, this.email, this.lastName, this.firstName, this.midName);
    }
}
