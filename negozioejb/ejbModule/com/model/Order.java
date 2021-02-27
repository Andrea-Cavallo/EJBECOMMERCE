package com.model;

import java.io.Serializable;

public class Order  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id ;
	private Integer id_user;	
	private Double totalprice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public Order(Integer id, Integer id_user, Double totalprice) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.totalprice = totalprice;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", id_user=" + id_user + ", totalprice=" + totalprice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((totalprice == null) ? 0 : totalprice.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		if (totalprice == null) {
			if (other.totalprice != null)
				return false;
		} else if (!totalprice.equals(other.totalprice))
			return false;
		return true;
	}


}
