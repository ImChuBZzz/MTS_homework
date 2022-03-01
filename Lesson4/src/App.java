import data.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import services.UserService;
import services.impl.UserServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) {


        //UserRepositoryImpl ur = new UserRepositoryImpl();
        UserService us = new UserServiceImpl();

        us.createUser("Иванов", "Иван", "Иванович", "3331757", "ivan@vdome.ru");
        us.createUser("Иванов", "Иван", "Петрович", "2221757", "ivan@nazavode.ru");
        us.createUser("Иванов", "Петр", "Кузьмич", "33317572", "petya@vdome.ru");
        us.createUser("Петров", "Иван", "Сидорович", "33357", "ivan@kgb.ru");

        System.out.println(us.getUsers("33357"));
        System.out.println(us.getUsers("Иванов Иван Иванович"));
        System.out.println(us.getUsers("ivan@nazavode.ru"));






    }

}
