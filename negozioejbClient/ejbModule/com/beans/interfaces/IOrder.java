package com.beans.interfaces;

import java.sql.SQLException;

import com.dto.OrderDTO;



public interface IOrder {
	
	void add(OrderDTO o) throws SQLException;
	OrderDTO findById(Integer id) throws SQLException;
	void delete(Integer id) throws SQLException;

}
