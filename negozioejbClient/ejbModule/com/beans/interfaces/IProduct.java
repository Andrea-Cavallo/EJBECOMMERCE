package com.beans.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.dto.ProductDTO;

public interface IProduct {
	
	void add(ProductDTO p) throws SQLException;
	ProductDTO findById(Integer id);
	void delete(Integer id) throws SQLException;
	void update(ProductDTO p) throws SQLException, NamingException;
	List<ProductDTO> findByName(String name) throws SQLException;
	ArrayList<ProductDTO> findAll() throws SQLException;
	ArrayList<ProductDTO> findByDesc(String desc) throws SQLException;
	List<ProductDTO> getRecords(int start,int total) throws SQLException;

}
