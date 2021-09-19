package com.exam.services.auth;

import com.exam.models.auth.Role;
import com.exam.models.auth.User;
import com.exam.repos.auth.RoleRepository;
import com.exam.repos.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user, Set<Role> roles) {
        User local_user = userRepository.findByUsername(user.getUsername());
        if(local_user!=null){
            System.out.println("User Already Exists in dataBase");
            return null;
        }
        for(Role role: roles){
            roleRepository.save(role);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().addAll(roles);
        return userRepository.save(user);
    }
    public  User getUser(String username){
        return this.userRepository.findByUsername(username);
    }

    public boolean deleteUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty())
            return false;

        userRepository.deleteById(id);
        return true;
    }
}
