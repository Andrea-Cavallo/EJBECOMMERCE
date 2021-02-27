package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.model.Product;


public interface IProductBeanDao {
	void add(Product p) throws SQLException;
	Product findById(Integer id);
	void delete(Integer id) throws SQLException;
	void update(Product p) throws SQLException, NamingException;
	List<Product> findByName(String name) throws SQLException;
	ArrayList<Product> findAll() throws SQLException;
	ArrayList<Product> findByDesc(String desc) throws SQLException;
	List<Product> getRecords(int start,int total) throws SQLException;

}
