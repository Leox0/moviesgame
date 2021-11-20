package com.kaczart.moviesweb.user.controller;

import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody RequestUser requestUser) throws Exception {
        return ResponseEntity.ok().body(userService.createUser(requestUser));
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createAdmin(@RequestBody RequestUser requestUser) throws Exception {
        return ResponseEntity.ok().body(userService.createAdmin(requestUser));
    }
}
