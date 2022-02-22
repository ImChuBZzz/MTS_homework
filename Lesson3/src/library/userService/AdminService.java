package library.userService;

import library.data.Client;

public interface AdminService {
    void banUser(Client client);
    void unbanUser(Client client);
}
