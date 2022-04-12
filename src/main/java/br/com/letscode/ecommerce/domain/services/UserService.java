package br.com.letscode.ecommerce.domain.services;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<UserEntity> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();

        return users;
    }

    public UserEntity createUser(UserEntity user) {
        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdatedAt(ZonedDateTime.now());

        return  this.userRepository.save(user);
    }

    public void deleteUserByID(Long id) {
        this.userRepository.findById(id)
                .map( userExists -> {
                    this.userRepository.delete(userExists);
                    return userExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public void updateUserById(UserEntity user,Long id) {
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
