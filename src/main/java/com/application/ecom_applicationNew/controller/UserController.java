package com.application.ecom_applicationNew.controller;

import com.application.ecom_applicationNew.dto.UserResponse;
import com.application.ecom_applicationNew.service.UserService;
import com.application.ecom_applicationNew.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//Add new API's to this controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
       return new ResponseEntity<>(userService.fetchAllUser(), HttpStatus.OK);
    }

    // Adding users

    @PostMapping("api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User added", HttpStatus.CREATED);

        //added
    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUserByIds(@PathVariable Long id){
//        User user = userService.fetchUserById(id);
//        if (user == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);

        return userService.fetchUserById(id)
                .map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        boolean updated = userService.updateUser(id, updatedUser);
        if(updated)
            return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }

}

