package com.dao;

import java.sql.SQLException;

import com.model.Order;

public interface IOrderBeanDao {

	
	void add(Order o) throws SQLException;
	Order findById(Integer id) throws SQLException;
	void delete(Integer id) throws SQLException;
}
