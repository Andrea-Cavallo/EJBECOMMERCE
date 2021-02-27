package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dao.IOrderBeanDao;
import com.exceptions.RecordNonTrovatoException;
import com.model.Order;

public class OrderBeanDaoImpl implements IOrderBeanDao {

	@Override
	public void add(Order o) throws SQLException {
		Connection con = null;
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "INSERT INTO negozioejb.order(id_user,totalprice) VALUES(?,?) ";
			PreparedStatement cmd = con.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);
			cmd.setInt(1, o.getId_user());
			cmd.setDouble(2, o.getTotalprice());
			cmd.executeUpdate();
		} catch (Exception e) {
			System.err.println("errore generale contattare l'amministratore");
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	@Override
	public Order findById(Integer id) throws SQLException {
		Order o = new Order();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "SELECT * FROM order WHERE ID = ? ";
			PreparedStatement cmd = con.prepareStatement(qry);
			cmd.setInt(1, id);
			ResultSet res = cmd.executeQuery();
			boolean esiste = res.next();
			if (!esiste)
				throw new RecordNonTrovatoException("Ordine trovato!!");
			while (esiste) {
				o = makeOrdersFromResultSet(res);
				esiste = res.next();
			}
			return o;
		} catch (Exception e) {
			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		Connection con = null;
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "DELETE FROM ORDER WHERE ID = ?";
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
	private Order makeOrdersFromResultSet(ResultSet rs) throws SQLException {
		Order orders = new Order();
		orders.setId(rs.getInt("id"));
		orders.setId_user(rs.getInt("id_user"));
		orders.setTotalprice(rs.getDouble("totalprice"));
		return orders;
	}

}
