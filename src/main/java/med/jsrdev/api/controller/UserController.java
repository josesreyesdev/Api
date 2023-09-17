package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.user_example.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user-examples")
public class UserController {

    @Autowired
    private UserExampleRepository userRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserExampleDataResponse> addUser(
            @RequestBody @Valid AddUserExampleData userData, UriComponentsBuilder uri) {
        UserExample user = userRepository.save(new UserExample(userData));

        URI url = uri.path("/user-examples/register/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(
                new UserExampleDataResponse(user.getId(), user.getTitle(), user.getBody())
        );
    }

    // Metodo que retorna los datos ingresados para un registro
    @GetMapping("/register/{id}")
    public ResponseEntity<UserExampleDataResponse> returnUserData(@PathVariable Long id) {
        UserExample user = userRepository.getReferenceById(id);

        return ResponseEntity.ok(new UserExampleDataResponse(user.getId(), user.getTitle(), user.getBody()));
    }

    /*@GetMapping
    public List<UserDataList> userList() {
        return userRepository.findAll().stream().map(UserDataList::new).toList();
    } */

    // Get All Users
    @GetMapping
    public ResponseEntity<Page<GetUserExampleDataList>> getUserList(Pageable page) {
        return ResponseEntity.ok(userRepository.findAll(page).map(GetUserExampleDataList::new));
    }

    //Get Active Users
    @GetMapping("/get-active")
    public ResponseEntity<Page<GetUserExampleDataList>> getActiveUserList(@PageableDefault(size = 4) Pageable pagination)  {
        return ResponseEntity.ok(userRepository.findByActiveTrue(pagination).map(GetUserExampleDataList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserExampleDataResponse> updateUser(@RequestBody @Valid UpdateUserExampleData updateUser) {
        UserExample user = userRepository.getReferenceById(updateUser.id());
        user.updateUser(updateUser);

        return ResponseEntity.ok(new UserExampleDataResponse(user.getId(), user.getTitle(), user.getBody()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid @PathVariable Long id) {
        UserExample user  = userRepository.getReferenceById(id);
        user.deactivateUser();

        return ResponseEntity.noContent().build();
    }
}