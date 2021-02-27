package com.beans.interfaces;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.dto.UserDTO;
import com.exceptions.RecordNonTrovatoException;



public interface IUser {
	
	void add(UserDTO u) throws SQLException;
	UserDTO findById(Integer id) throws SQLException;
	void delete(Integer id) throws SQLException;
	void update(UserDTO u) throws SQLException, NamingException;
    UserDTO Login(String username,String password) throws SQLException, RecordNonTrovatoException, NamingException;
}
