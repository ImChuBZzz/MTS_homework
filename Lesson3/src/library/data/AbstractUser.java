package library.data;

public class AbstractUser {
    private String surname;
    private String name;
    private String secondName;
    private String phoneNumber;

    public AbstractUser(String surname, String name,  String secondName,  String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
    }

    public  AbstractUser() {}

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public String getSecondName() { return secondName; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setSurname(String surname) { this.surname = surname; }
    public void setName(String name) { this.name = name; }
    public void setSecondName(String secondName) { this.secondName = secondName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
