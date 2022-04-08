package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity index() {
        List<UserEntity> users = this.userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity store (@RequestBody UserEntity user){

        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdatedAt(ZonedDateTime.now());

        System.out.println(user);

        UserEntity userSaved = this.userRepository.save(user);

        return  ResponseEntity.ok(userSaved);
    }



}
