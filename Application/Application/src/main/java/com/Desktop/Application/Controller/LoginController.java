package com.Desktop.Application.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Desktop.Application.Dto.usermasterDto;
import com.Desktop.Application.Entity.usermaster;
import com.Desktop.Application.Service.usermasterService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class LoginController {
    @Autowired
    private usermasterService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody usermaster user) {
        usermaster createdUser = service.signup(user);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("user", createdUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody usermasterDto dto) {
        Optional<usermaster> user = service.login(dto.getUsername(), dto.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (user.isPresent()) {
            response.put("success", true);
            response.put("user", user.get());
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
        }
        return ResponseEntity.ok(response);
    }
}
