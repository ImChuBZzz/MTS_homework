package library.userService;

import library.data.Client;

public interface AdminService {
    void ban(Client client);
    void unBan(Client client);
}
