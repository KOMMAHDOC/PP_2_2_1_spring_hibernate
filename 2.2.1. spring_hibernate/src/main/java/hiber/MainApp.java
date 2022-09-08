package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User user1 = new User("User5", "Lastname5", "user5@mail.ru");
        user1.setCar(new Car("BMW", 5));
        userService.add(user1);

        user1 = new User("User6", "Lastname6", "user6@mail.ru");
        user1.setCar(new Car("Mazda", 6));
        userService.add(user1);

        user1 = new User("User7", "Lastname7", "user7@mail.ru");
        user1.setCar(new Car("BMW", 7));
        userService.add(user1);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }

        System.out.println(userService.getUser(new Car("BMW", 7)));
        System.out.println(userService.getUser(new Car("BMW", 5)));

        context.close();
    }
}
