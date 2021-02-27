package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dao.IProductBeanDao;
import com.exceptions.RecordNonTrovatoException;
import com.model.Product;

public class ProductBeanDaoImpl implements IProductBeanDao {
	@Override
	public void add(Product p) throws SQLException {
		Connection con = null;
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "INSERT INTO product(name,description,price) VALUES(?,?,?) ";
			PreparedStatement cmd = con.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);

			cmd.setString(1, p.getName());
			cmd.setString(2, p.getDescription());
			cmd.setDouble(3, p.getPrice());
			cmd.executeUpdate();

		} catch (Exception e) {
			System.err.println("errore generale contattare l'amministratore");
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	@Override
	public Product findById(Integer id) {
		Product p = new Product();
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			Connection con = ds.getConnection();

			String qry = "SELECT * FROM product WHERE ID = ? ";

			PreparedStatement cmd = con.prepareStatement(qry);

			cmd.setInt(1, id);

			ResultSet res = cmd.executeQuery();

			boolean esiste = res.next();
			if (!esiste)
				throw new RecordNonTrovatoException("Prodotto non trovato!!");

			while (esiste) {
				p.setId(res.getInt("id"));
				p.setName(res.getString("name"));
				p.setDescription(res.getString("description"));
				p.setPrezzo(res.getDouble("price"));
				esiste = res.next();
			}
			return p;

		} catch (Exception e) {

			System.err.println("ERRORE");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Product p) throws SQLException, NamingException {
		String sql = "update USER set username=?,password=? where id=?";

		Connection con = null;

		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			PreparedStatement cmd = con.prepareStatement(sql);

			cmd.setString(1, p.getName());
			cmd.setString(2, p.getDescription());
			cmd.setDouble(3, p.getPrice());
			cmd.executeUpdate();

		} finally {
			con.close();
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();
			String qry = "DELETE FROM PRODUCT WHERE ID = ?";
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

	@Override
	public ArrayList<Product> findByName(String name) throws SQLException {
		ArrayList<Product> listaProdotti = new ArrayList<>();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "SELECT * FROM product WHERE name = ? ";
			PreparedStatement cmd = con.prepareStatement(qry);
			cmd.setString(1, name);
			ResultSet res = cmd.executeQuery();
			boolean esiste = res.next();

			while (esiste) {

				listaProdotti.add(makeProductFromResultSet(res));
				esiste = res.next();
			}
			return listaProdotti;

		} catch (Exception e) {

			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

	@Override
	public ArrayList<Product> findAll() throws SQLException {

		ArrayList<Product> listaProdotti = new ArrayList<>();
		Connection con = null;
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "SELECT * FROM product";
			PreparedStatement cmd = con.prepareStatement(qry);
			ResultSet res = cmd.executeQuery();

			while (res.next()) {

				Product p = makeProductFromResultSet(res);
				listaProdotti.add(p);
			}
			return listaProdotti;

		} catch (Exception e) {
			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

	private Product makeProductFromResultSet(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setDescription(rs.getString("description"));
		p.setPrezzo(rs.getDouble("price"));

		return p;
	}

	@Override
	public ArrayList<Product> findByDesc(String description) throws SQLException {
		ArrayList<Product> listaProdotti = new ArrayList<>();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "SELECT * FROM product order by price asc ";
			PreparedStatement cmd = con.prepareStatement(qry);
			ResultSet res = cmd.executeQuery();
			boolean esiste = res.next();
			while (esiste) {
				Product p = makeProductFromResultSet(res);
				listaProdotti.add(p);
				esiste = res.next();
			}
			return listaProdotti;
		} catch (Exception e) {
			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

	@Override
	public List<Product> getRecords(int start, int total) throws SQLException {
		List<Product> listaProdotti = new ArrayList<>();
		Connection con = null;
		try {

			DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/MySqlDS");
			con = ds.getConnection();

			String qry = "select * from product limit start-1,total; ";
			PreparedStatement cmd = con.prepareStatement(qry);
			ResultSet res = cmd.executeQuery();
			boolean esiste = res.next();

			while (esiste) {

				listaProdotti.add(makeProductFromResultSet(res));
				esiste = res.next();
			}
			return listaProdotti;

		} catch (Exception e) {

			System.err.println("ERRORE");
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

}
