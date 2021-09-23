package com.exam.controllers.auth;


import com.exam.models.auth.Role;
import com.exam.models.auth.User;
import com.exam.models.portal.Student;
import com.exam.repos.auth.RoleRepository;
import com.exam.services.auth.UserService;
import com.exam.services.portal.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StudentService studentService;

    @GetMapping("/test")
    public String test(){
        return "APIs working";
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<Role> byId = roleRepository.findById(14L);

        Role guestRole = byId.get();
        Set<Role> roles = new HashSet<>();
        roles.add(guestRole);
        User userFetched = this.userService.createUser(user,roles);
        if(userFetched!=null){
            Student student = new Student();
            student.setUserId(userFetched.getId());
            studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.OK).body(userFetched);
        }
        return ResponseEntity.badRequest().body("User Already Exists!");
    }


    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.badRequest().body("User Not Found!");

    }
}
