package library.libraryService;

import library.data.Admin;
import library.data.Book;
import library.data.Client;

import java.util.List;

public interface LibraryAPIService {
    void ban(Admin admin, Client client);
    void unBan(Admin admin, Client client);
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
