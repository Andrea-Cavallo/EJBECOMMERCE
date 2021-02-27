package com.builder;

import com.dto.OrderDTO;
import com.model.Order;

public class OrderDTOBuilder {

	
	public static Order dtoToModel(OrderDTO udto) {

		Order o = new Order();
		o.setId(udto.getId());
		o.setId_user(udto.getId_user());
		o.setTotalprice(udto.getTotalprice());
		return o;
	}
	
	public static  OrderDTO modelToDto(Order o) {
		OrderDTO odto = new OrderDTO();
		odto.setId(o.getId());
		odto.setId_user(o.getId_user());
		odto.setTotalprice(o.getTotalprice());
		
		return odto;

	}
	
	
	
}
