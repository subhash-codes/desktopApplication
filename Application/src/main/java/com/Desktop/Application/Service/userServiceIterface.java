package com.Desktop.Application.Service;

import java.util.Optional;

import com.Desktop.Application.Dto.usermasterDto;
import com.Desktop.Application.Entity.usermaster;

public interface userServiceIterface {

	 public usermaster signup(usermaster userdto);
	 
	 public Optional<usermaster>login(String username, String password);
	 
}
