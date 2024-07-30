package com.Desktop.Application.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Desktop.Application.Entity.usermaster;
import com.Desktop.Application.Repository.UserRepository;

@Service
public class usermasterService implements userServiceIterface {
	
    @Autowired
	private UserRepository repo;
	@Override
    public usermaster signup(usermaster user) {
        user.setCreatedon(LocalDate.now());
        user.setPassword(user.getPassword());
        return repo.save(user);
    }
	
	@Override
	public Optional<usermaster>login(String username, String password) {
		Optional<usermaster> user = repo.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
	   
	}

