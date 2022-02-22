package library.libraryService;

import library.data.Admin;
import library.data.Book;
import library.data.Client;


import java.util.List;

public interface LibraryAPIService {
    void banUser(Admin admin, Client client);
    void unbanUser(Admin admin, Client client);
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
