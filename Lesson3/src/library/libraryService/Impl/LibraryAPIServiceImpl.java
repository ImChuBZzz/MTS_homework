package library.libraryService.Impl;

import library.data.Admin;
import library.data.Book;
import library.data.Client;
import library.libraryService.LibraryAPIService;
import library.userService.AdminService;
import library.userService.ClientService;

import java.util.List;

public class LibraryAPIServiceImpl implements LibraryAPIService {
    private final AdminService adminService;
    private final ClientService clientService;

    public LibraryAPIServiceImpl(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @Override
    public void banUser(Admin admin, Client client) {
        adminService.banUser(client);
        System.out.printf("Admin %s successfully banned client %s \n", admin.getPhoneNumber(), client.getPhoneNumber());
    }

    @Override
    public void unbanUser(Admin admin, Client client) {
        adminService.unbanUser(client);
        System.out.printf("Admin %s successfully unbanned client %s \n", admin.getPhoneNumber(), client.getPhoneNumber());
    }

    @Override
    public void takeBooks(Client client, List<Book> books) {
        if (client.isBanned()) {
            System.out.printf("Client %s can't take book coz he is banned\n", client.getPhoneNumber());
            return;
        }
        clientService.takeBooks(client, books);
        System.out.printf("Client %s takes %s\n", client.getPhoneNumber(), books);
    }

    @Override
    public void returnBooks(Client client, List<Book> books) {
        clientService.returnBooks(client, books);
        System.out.printf("Client %s returned %s\n", client.getPhoneNumber(), books);
    }
}
