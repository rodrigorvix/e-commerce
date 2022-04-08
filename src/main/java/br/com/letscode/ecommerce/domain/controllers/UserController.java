package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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
  /*  public void delete(@PathVariable Long id) {

        this.userRepository
                .findById(id)
                .map( userExists -> this.userRepository.delete(userExists))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody UserEntity user, @PathVariable Long id) {

       return  this.userRepository
                .findById(id)
               .map(userExists -> {
                   user.setId(userExists.getId());
                   this.userRepository.save(user);
                   return ResponseEntity.noContent().build();
               }).orElseGet( () -> ResponseEntity.notFound().build() );

    }



}
