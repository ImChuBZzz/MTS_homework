package library.userService.Impl;

import library.data.Book;
import library.data.Client;
import library.userService.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public void takeBooks(Client client, List<Book> books) {
        List<Book> _books = client.getBooks();
        _books.addAll(books);
        client.setBooks(_books);
    }
    @Override
    public void returnBooks(Client client, List<Book> books) {
        List<Book> _books = client.getBooks();
        for(Book book: books) { _books.remove(book); }
        client.setBooks(_books);
    }
}
