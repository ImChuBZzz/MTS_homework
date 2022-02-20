package library.data;

import java.util.ArrayList;
import java.util.List;

public class Client extends AbstractUser {
    private boolean isBanned;
    private List<Book> books;

    public Client(String surname, String name,  String secondName, String phoneNUmber) {
        super(surname, name, secondName, phoneNUmber);
        this.isBanned = false;
        this.books = new ArrayList<>();
    }

    public boolean isBanned() { return isBanned; }
    public void setBanned(boolean banned) { isBanned = banned; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

}
