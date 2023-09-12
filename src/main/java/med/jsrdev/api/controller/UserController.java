package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.medic.GetMedicalDataList;
import med.jsrdev.api.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/example-user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Transactional
    public void addUser(@RequestBody @Valid AddUserData user) {
        userRepository.save(new User(user));
    }

    @PostMapping("/register-users")
    @Transactional
    public void addUserList(@RequestBody @Valid List<AddUserData> users) {
        users.forEach(user -> userRepository.save(new User(user)));
    }

    /*@GetMapping
    public List<UserDataList> userList() {
        return userRepository.findAll().stream().map(UserDataList::new).toList();
    } */

    // Get All Users
    @GetMapping
    public Page<GetUserDataList> getUserList(Pageable page) {
        return userRepository.findAll(page).map(GetUserDataList::new);
    }

    //Get Active Users

    @GetMapping("/get-active")
    public Page<GetUserDataList> getActiveUserList(@PageableDefault(size = 4) Pageable pagination)  {
        return userRepository.findByActiveTrue(pagination).map(GetUserDataList::new);
    }

    @PutMapping
    @Transactional
    public void updateUser(@RequestBody @Valid UpdateUserData updateUser) {
        User user = userRepository.getReferenceById(updateUser.id());
        user.updateUser(updateUser);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@RequestBody @Valid @PathVariable Long id) {
        User user  = userRepository.getReferenceById(id);
        user.deactivateUser();
    }
}