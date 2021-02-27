package com.model;

import java.io.Serializable;

public class ProductOrder implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer id_product;
	private Integer id_order;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_product() {
		return id_product;
	}
	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}
	public Integer getId_order() {
		return id_order;
	}
	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}
	public ProductOrder(Integer id, Integer id_product, Integer id_order) {
		super();
		this.id = id;
		this.id_product = id_product;
		this.id_order = id_order;
	}
	public ProductOrder() {};
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_order == null) ? 0 : id_order.hashCode());
		result = prime * result + ((id_product == null) ? 0 : id_product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrder other = (ProductOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_order == null) {
			if (other.id_order != null)
				return false;
		} else if (!id_order.equals(other.id_order))
			return false;
		if (id_product == null) {
			if (other.id_product != null)
				return false;
		} else if (!id_product.equals(other.id_product))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProductOrder [id=" + id + ", id_product=" + id_product + ", id_order=" + id_order + "]";
	}
	
	
	
	
}
