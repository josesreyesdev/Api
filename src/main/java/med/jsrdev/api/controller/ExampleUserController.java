package med.jsrdev.api.controller;

import med.jsrdev.api.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/example-user")
public class ExampleUserController {

    @PostMapping
    public void registerUser(@RequestBody List<User> users) {
        System.out.println("RequestUser llegó correctamente");
        users.forEach(System.out::println);
    }
}
