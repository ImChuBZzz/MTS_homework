package library.userService.Impl;

import library.data.Book;
import library.data.Client;
import library.userService.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public void takeBooks(Client client, List<Book> books) {
        List<Book> clientBooks = client.getBooks(); // нижние подчеркивание в джаве не принято использовать в начале переменных)
        clientBooks.addAll(books);
        client.setBooks(clientBooks);
    }
    @Override
    public void returnBooks(Client client, List<Book> books) {
        List<Book> clientBooks = client.getBooks();
        clientBooks.removeAll(books); // можно removeAll заюзать
        client.setBooks(clientBooks);
    }
}
