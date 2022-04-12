package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import br.com.letscode.ecommerce.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;


@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*public UserController (UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping()
    public List<UserEntity> getUsers() {

        return this.userService.getUsers();

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser (@RequestBody UserEntity user){

        return this.userService.createUser(user);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {

        this.userService.deleteUserByID(id);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserById(@RequestBody UserEntity user, @PathVariable Long id) {

        this.userService.updateUserById(user,id);

    }

}
