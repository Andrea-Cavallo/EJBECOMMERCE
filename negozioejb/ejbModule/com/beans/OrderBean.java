package com.beans;

import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.builder.OrderDTOBuilder;
import com.dao.IOrderBeanDao;
import com.dao.impl.OrderBeanDaoImpl;
import com.dto.OrderDTO;
import com.model.Order;


@Stateless
@Remote
public class OrderBean implements Serializable, OrderBeanRemote {

	private static final long serialVersionUID = 1L;

	public OrderBean() {
	}
	private IOrderBeanDao  odao = new OrderBeanDaoImpl();

	@Override
	public void add(OrderDTO odto) throws SQLException {
		Order o = OrderDTOBuilder.dtoToModel(odto);
		odao.add(o);
	}

	@Override
	public OrderDTO findById(Integer id) throws SQLException {
		Order o = new Order();
		o = odao.findById(id);

		OrderDTO odto = OrderDTOBuilder.modelToDto(o);
		
		return odto;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		odao.delete(id);
	}

}
