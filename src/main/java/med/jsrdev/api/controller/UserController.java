package med.jsrdev.api.controller;

import jakarta.validation.Valid;
import med.jsrdev.api.user.User;
import med.jsrdev.api.user.UserDataList;
import med.jsrdev.api.user.UserRegistrationData;
import med.jsrdev.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example-user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void registerUser(@RequestBody @Valid List<UserRegistrationData> users) {
        users.forEach(user -> userRepository.save(new User(user)));
    }

    @GetMapping
    public List<UserDataList> userList() {
        return userRepository.findAll().stream().map(UserDataList::new).toList();
    }
}