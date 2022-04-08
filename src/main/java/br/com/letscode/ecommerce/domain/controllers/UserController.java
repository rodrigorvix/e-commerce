package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;


@RequestMapping("/api/users")
@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<UserEntity> index() {
        List<UserEntity> users = this.userRepository.findAll();

        return users;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity store (@RequestBody UserEntity user){

        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdatedAt(ZonedDateTime.now());

        return  this.userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userRepository.findById(id)
                .map( userExists -> {
                    this.userRepository.delete(userExists);
                    return userExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserEntity user, @PathVariable Long id) {

         this.userRepository
                .findById(id)
               .map(userExists -> {
                   user.setId(userExists.getId());
                   user.setCreatedAt(userExists.getCreatedAt());
                   user.setUpdatedAt(ZonedDateTime.now());
                   this.userRepository.save(user);
                   return userExists;
               }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

    }



}
