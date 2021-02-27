package com.beans;

import java.io.Serializable;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import com.builder.UserDTOBuilder;
import com.dao.IUserBeanDao;
import com.dao.impl.UserBeanDaoImp;
import com.dto.UserDTO;
import com.exceptions.RecordNonTrovatoException;
import com.model.User;

@Stateless
@Remote
public class UserBean implements UserBeanRemote, Serializable {

	private IUserBeanDao ub = new UserBeanDaoImp();
	private static final long serialVersionUID = 1L;

	public UserBean() {
	}

	@Override
	public void add(UserDTO udto) throws SQLException {
		User u = UserDTOBuilder.dtoToModel(udto);
	
		ub.add(u);
	}

	@Override
	public UserDTO findById(Integer id) throws SQLException {
		User u = new User();
		u = ub.findById(id);
		UserDTO udto = UserDTOBuilder.modelToDto(u);
		return udto;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		
         ub.delete(id);
	}

	@Override
	public void update(UserDTO udto) throws SQLException, NamingException {
		User u = UserDTOBuilder.dtoToModel(udto);
		ub.update(u);
	}

	@Override
	public UserDTO Login(String username, String password) throws SQLException, RecordNonTrovatoException, NamingException {
		
		User u = new User();
		u = ub.Login(username, password);
		UserDTO udto = UserDTOBuilder.modelToDto(u);
		
		return udto;
	}

}
