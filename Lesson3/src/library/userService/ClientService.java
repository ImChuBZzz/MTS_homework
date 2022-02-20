package library.userService;

import library.data.Book;
import library.data.Client;
import java.util.List;

public interface ClientService {
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
