package br.com.diego.estudos.movierecommendation.user.application;


import br.com.diego.estudos.movierecommendation.user.domain.User;
import br.com.diego.estudos.movierecommendation.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
