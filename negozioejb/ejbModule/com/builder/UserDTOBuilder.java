package com.builder;

import com.dto.UserDTO;
import com.model.User;

public class UserDTOBuilder {

	public static User dtoToModel(UserDTO udto) {
		User u = new User();
		u.setId(udto.getId());
		u.setUsername(udto.getUsername());
		u.setPassword(udto.getPassword());
		return u;
	}
	
	public static  UserDTO modelToDto(User u) {
		UserDTO udto = new UserDTO();
		udto.setId(u.getId());
		udto.setUsername(u.getUsername());
		udto.setPassword(u.getPassword());
		return udto;

	}

}
