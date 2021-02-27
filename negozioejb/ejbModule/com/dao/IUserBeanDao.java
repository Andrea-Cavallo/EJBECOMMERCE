package com.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.exceptions.RecordNonTrovatoException;
import com.model.User;

public interface IUserBeanDao {

	void add(User u) throws SQLException;
	User findById(Integer id) throws SQLException;
	void delete(Integer id) throws SQLException;
	void update(User u) throws SQLException, NamingException;
    User Login(String username,String password) throws SQLException, RecordNonTrovatoException, NamingException;
	
	
}
