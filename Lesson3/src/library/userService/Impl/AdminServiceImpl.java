package library.userService.Impl;

import library.data.Client;
import library.userService.AdminService;

public class AdminServiceImpl implements AdminService {
    @Override
    public void ban(Client client) {
        client.setBanned(true);
    }

    @Override
    public void unBan(Client client) {
        client.setBanned(false);
    }
}
