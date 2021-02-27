package com.beans;

import java.io.Serializable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import com.builder.ProductDTOBuilder;
import com.dao.IProductBeanDao;
import com.dao.impl.ProductBeanDaoImpl;
import com.dto.ProductDTO;
import com.model.Product;

@Stateless
@Remote
public class ProductBean implements ProductBeanRemote, Serializable {

	private static final long serialVersionUID = 1L;

	public ProductBean() {
	}

	private IProductBeanDao pb = new ProductBeanDaoImpl();

	@Override
	public void add(ProductDTO pdto) throws SQLException {
		Product p = ProductDTOBuilder.dtoToModel(pdto);
		pb.add(p);
	}

	@Override
	public ProductDTO findById(Integer id) {
		Product p = new Product();
		p = pb.findById(id);
		ProductDTO pdto = ProductDTOBuilder.modelToDto(p);
		return pdto;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		pb.delete(id);
	}

	@Override
	public void update(ProductDTO pdto) throws SQLException, NamingException {
		Product p = ProductDTOBuilder.dtoToModel(pdto);
		pb.update(p);

	}

	@Override
	public List<ProductDTO> findByName(String name) throws SQLException {
		List<Product> pList = pb.findByName(name);
		List<ProductDTO> pDlist = new ArrayList<>();
		for (Product p : pList) {
			ProductDTO pdto = ProductDTOBuilder.modelToDto(p);
			pDlist.add(pdto);
		}

		return pDlist;
	}

	@Override
	public ArrayList<ProductDTO> findAll() throws SQLException {
		ArrayList<Product> pList = pb.findAll();
		ArrayList<ProductDTO> pDlist = new ArrayList<>();
		for (Product p : pList) {
			ProductDTO pdto = ProductDTOBuilder.modelToDto(p);
			pDlist.add(pdto);
		}

		return pDlist;
	}

	@Override
	public ArrayList<ProductDTO> findByDesc(String desc) throws SQLException {
		ArrayList<Product> pList = pb.findByDesc(desc);
		ArrayList<ProductDTO> pDlist = new ArrayList<>();
		for (Product p : pList) {
			ProductDTO pdto = ProductDTOBuilder.modelToDto(p);
			pDlist.add(pdto);
		}

		return pDlist;
	}

	@Override
	public List<ProductDTO> getRecords(int start, int total) throws SQLException {
 // da aggiungere 
		return null;
	}

}
