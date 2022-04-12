package br.com.letscode.ecommerce.domain.services;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import br.com.letscode.ecommerce.exception.RolesException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public List<UserEntity> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();

        return users;
    }

    public UserEntity createUser(UserEntity user) {


       List<UserEntity> usernameSearch = this.userRepository.findAll()
               .stream()
               .filter(userExists -> userExists.getUsername().equals(user.getUsername()))
               .collect(Collectors.toList());

        System.out.println(usernameSearch.size());

       if(usernameSearch.size() > 0) {
           throw  new RolesException("Usuário já existe.");
       }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não existe.");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getFunction()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
