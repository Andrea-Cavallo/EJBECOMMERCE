package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dao.IUserBeanDao;
import com.exceptions.RecordNonTrovatoException;
import com.model.User;

public class UserBeanDaoImp implements IUserBeanDao {
	
	public void add(User u) throws SQLException {
		Connection con = null;
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "INSERT INTO user(username,password) VALUES(?,?) ";
			PreparedStatement cmd = con.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

			cmd.setString(1, u.getUsername());
			cmd.setString(2, u.getPassword());
			cmd.executeUpdate();

		} catch (Exception e) {
			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	public User findById(Integer id) throws SQLException {
		User u = new User();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "SELECT * FROM user WHERE ID = ? ";

			PreparedStatement cmd = con.prepareStatement(qry);

			cmd.setInt(1, id);

			ResultSet res = cmd.executeQuery();

			boolean esiste = res.next();
			while (esiste) {
				u.setUsername("username");
				u.setPassword("password");
				esiste = res.next();
			}

		} catch (Exception e) {

			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return u;
	}

	public void delete(Integer id) throws SQLException {
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "DELETE FROM USER WHERE ID = ?";

			PreparedStatement cmd = con.prepareStatement(qry);

			cmd.setInt(1, id);

			cmd.executeUpdate();

		} catch (Exception e) {

			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	
	public void update(User u) throws SQLException, NamingException {
		String sql = "update USER set username=?,password=? where id=?";

		Connection con = null;

		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setString(1, u.getUsername());
			cmd.setString(2, u.getPassword());

			cmd.setInt(3, u.getId());

			cmd.executeUpdate();

		} finally {
			con.close();
		}
	}

	public User Login(String username, String password) throws SQLException, RecordNonTrovatoException, NamingException {
		User u = new User();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "SELECT * FROM user WHERE username = ? and password =? ";

			PreparedStatement cmd = con.prepareStatement(qry);

			cmd.setString(1, username);
			cmd.setString(2, password);

			ResultSet res = cmd.executeQuery();

			boolean esiste = res.next();
			if (!esiste)
				throw new RecordNonTrovatoException("Credenziali sbagliate");
			while (esiste) {
				u.setId(res.getInt("id"));
				u.setUsername(res.getString("username"));
				u.setPassword(res.getString("password"));
				esiste = res.next();
			}

		}  finally {
			con.close();
		}
		return u;
	}

}
