package library.userService.Impl;

import library.data.Client;
import library.userService.AdminService;

public class AdminServiceImpl implements AdminService {
    @Override
    public void banUser(Client client) {
        client.setBanned(true);
    }

    @Override
    public void unbanUser(Client client) {
        client.setBanned(false);
    }
}
