package library.libraryService.Impl;

import library.data.Admin;
import library.data.Book;
import library.data.Client;
import library.libraryService.LibraryAPIService;
import library.userService.AdminService;
import library.userService.ClientService;
import library.userService.Impl.AdminServiceImpl;
import library.userService.Impl.ClientServiceImpl;

import java.util.List;

public class LibraryAPIServiceImpl implements LibraryAPIService {
    private final AdminService adminService;
    private final ClientService clientService;

    public LibraryAPIServiceImpl(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @Override
    public void ban(Admin admin, Client client) {
        adminService.ban(client);
        System.out.printf("Admin %s successfully banned client %s \n", admin.getPhoneNumber(), client.getPhoneNumber());
    }

    @Override
    public void unBan(Admin admin, Client client) {
        adminService.unBan(client);
        System.out.printf("Admin %s successfully unbanned client %s \n", admin.getPhoneNumber(), client.getPhoneNumber());
    }

    @Override
    public void takeBooks(Client client, List<Book> books) {
        if(client.isBanned()) { System.out.printf("Client %s can't take book coz he is banned\n", client.getPhoneNumber()); }
        else {
            clientService.takeBooks(client, books);
            System.out.printf("Client %s takes %s\n", client.getPhoneNumber(), books);
        }
    }

    @Override
    public void returnBooks(Client client, List<Book> books) {
        clientService.returnBooks(client, books);
        System.out.printf("Client %s returned %s\n", client.getPhoneNumber(), books);
    }

}
