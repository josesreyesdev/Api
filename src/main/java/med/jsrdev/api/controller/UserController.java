package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDataResponse> addUser(
            @RequestBody @Valid AddUserData userData, UriComponentsBuilder uri) {
        User user = userRepository.save(new User(userData));

        URI url = uri.path("/users/register/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(
                new UserDataResponse(user.getId(), user.getTitle(), user.getBody())
        );
    }

    // Metodo que retorna los datos ingresados para un registro
    @GetMapping("/register/{id}")
    public ResponseEntity<UserDataResponse> returnUserData(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);

        return ResponseEntity.ok(new UserDataResponse(user.getId(), user.getTitle(), user.getBody()));
    }

    /*@GetMapping
    public List<UserDataList> userList() {
        return userRepository.findAll().stream().map(UserDataList::new).toList();
    } */

    // Get All Users
    @GetMapping
    public ResponseEntity<Page<GetUserDataList>> getUserList(Pageable page) {
        return ResponseEntity.ok(userRepository.findAll(page).map(GetUserDataList::new));
    }

    //Get Active Users
    @GetMapping("/get-active")
    public ResponseEntity<Page<GetUserDataList>> getActiveUserList(@PageableDefault(size = 4) Pageable pagination)  {
        return ResponseEntity.ok(userRepository.findByActiveTrue(pagination).map(GetUserDataList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDataResponse> updateUser(@RequestBody @Valid UpdateUserData updateUser) {
        User user = userRepository.getReferenceById(updateUser.id());
        user.updateUser(updateUser);

        return ResponseEntity.ok(new UserDataResponse(user.getId(), user.getTitle(), user.getBody()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid @PathVariable Long id) {
        User user  = userRepository.getReferenceById(id);
        user.deactivateUser();

        return ResponseEntity.noContent().build();
    }
}