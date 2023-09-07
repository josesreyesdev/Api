package med.jsrdev.api.controller;

import med.jsrdev.api.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example-user")
public class ExampleUserController {

    @PostMapping
    public void registerUser(@RequestBody List<User> users) {
        System.out.println("Request User llegó correctamente");
        users.forEach(System.out::println);
    }
}