package library;

import library.data.Admin;
import library.data.Book;
import library.data.Client;
import library.libraryService.Impl.LibraryAPIServiceImpl;
import library.libraryService.LibraryAPIService;
import library.userService.AdminService;
import library.userService.ClientService;
import library.userService.Impl.AdminServiceImpl;
import library.userService.Impl.ClientServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        ClientService clientService = new ClientServiceImpl();

        LibraryAPIService libraryAPIService = new LibraryAPIServiceImpl(adminService, clientService);

        Client client = new Client("Putin", "V.", "V.", "88005553535");
        Admin admin = new Admin("Smith", "Bob", "Marry-Ann", "555-2342-12");

        Book book1 = new Book("The Lord of the Rings", "J. R. R. Tolkien");
        Book book2 = new Book("The Witcher", "Andrzej Sapkowski");
        Book book3 = new Book("The Game of Thrones", "George R. R. Martin");

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        books1.add(book1);
        books1.add(book2);

        libraryAPIService.takeBooks(client, books1);
        System.out.println(client.getBooks());
        books2.add(book3);
        libraryAPIService.ban(admin, client);
        libraryAPIService.takeBooks(client, books2);
        System.out.println(client.getBooks());
        books1.remove(0);
        libraryAPIService.returnBooks(client, books1);
        System.out.println(client.getBooks());








    }
}
