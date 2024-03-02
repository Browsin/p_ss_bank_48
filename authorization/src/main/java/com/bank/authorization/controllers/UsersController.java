package com.bank.authorization.controllers;


import com.bank.authorization.entity.Users;
import com.bank.authorization.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }



//    @Operation(summary = "Return all profiles")
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }



//    @Operation(
//            summary = "Create a new profile",
//            description = "Allow to create new profile based on the received JSON-object"
//    )
    @PostMapping("/users")
    public ResponseEntity<?> addNewUser(@RequestBody Users newUser) {
        userService.createOrUpdateUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }

//    @Operation(summary = "Return profile by id")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Byte id) {
        Users user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }




//    @Operation(
//            summary = "Update profile info",
//            description = "Allow to change profile based on the received JSON-object and profile id"
//    )
    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Byte id, @RequestBody Users editUser) {
        return addNewUser(editUser);
    }




//    @Operation(summary = "Delete profile by id")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Byte id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }











//    @GetMapping("/authorizedInfo")
//    public ResponseEntity<?> infoAuthUser(Principal principal) {
//        return ResponseEntity.ok(userService.findByRole(principal.getName()));
//    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
//    }

//    @GetMapping("/users/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable("id") Byte id) {
//        Users user = userService.findById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

//
//    @PostMapping("/users")
//    public ResponseEntity<User> addNewUser(@RequestBody User newUser) {
//        userService.createOrUpdateUser(newUser);
//        return new ResponseEntity<>(newUser, HttpStatus.OK);
//
//    }
//
//    @PatchMapping("/users/{id}")
//    public ResponseEntity<User> updateUser(@RequestBody User editUser) {
//        return addNewUser(editUser);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Byte id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
