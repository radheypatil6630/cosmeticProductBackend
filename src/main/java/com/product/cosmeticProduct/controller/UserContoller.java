//package controller;
package com.product.cosmeticProduct.controller;


import com.product.cosmeticProduct.entity.User;
import com.product.cosmeticProduct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserContoller {

    @Autowired
    UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {


            User savedUser = userService.saveNewUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<?>> getUsers() {

        List<User> allusers = userService.getALlUser();

        if (allusers != null) {
            return new ResponseEntity<>(allusers, HttpStatus.ACCEPTED);
        }
        return  new ResponseEntity<>(allusers,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getUser(@RequestParam String username,@RequestParam String password) {
        System.out.println(username+" "+password);
                if(username == null && password == null){

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        boolean result = userService.findByUsername(username, password);
        if(!result ){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(result,HttpStatus.FOUND);
    }
}
