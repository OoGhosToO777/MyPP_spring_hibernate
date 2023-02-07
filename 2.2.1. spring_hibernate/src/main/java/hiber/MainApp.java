package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car_1 = new Car(7, "X");
      Car car_2 = new Car(5, "X");
      Car car_3 = new Car(5, "K");
      Car car_4 = new Car(3, "M");

      userService.add(car_1);
      userService.add(car_2);
      userService.add(car_3);
      userService.add(car_4);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car_1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car_2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car_3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car_4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }
      System.out.println(userService.findUserByCar("M", 3));
      context.close();
   }
}
