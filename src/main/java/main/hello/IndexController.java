package main.hello;

import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import main.registration.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Random;

@Controller
public class IndexController{

    private final UserRepository repository;
    private final Random random;

    @Autowired
    public IndexController(UserRepository userRepository, Random random) {
        this.repository = userRepository;
        this.random = random;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProcess(@RequestParam(value="username", required=false, defaultValue="empty") String username,
                                  @RequestParam(value="password", required=false, defaultValue="empty") String password,
                                  @RequestParam(value="password1", required=false, defaultValue="empty") String password1,
                                  @RequestParam(value="email", required=false, defaultValue="empty") String email,
                                  Model model) {

       model.addAttribute("username", username);
       model.addAttribute("password", password);
       model.addAttribute("password1", password1);
       model.addAttribute("email", email);


        repository.deleteAll();

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (User user : repository.findByLastName("Smith")) {
            System.out.println(user);
        }

        // save a couple of customers
        repository.save(new User("Alice", "Smith"));
        repository.save(new User("Bob", "Smith"));

        return "register_process";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerInterface(String name, Model model) {
       // model.addAttribute("name", "yo");
        return "register";
    }

}
